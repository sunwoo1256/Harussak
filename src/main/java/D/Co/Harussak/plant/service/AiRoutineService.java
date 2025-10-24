package D.Co.Harussak.plant.service;

import D.Co.Harussak.cultivation.repository.CultivationRepository;
import D.Co.Harussak.entity.Cultivation;
import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.entity.User;
import D.Co.Harussak.plant.dto.FlowerInfo;
import D.Co.Harussak.plant.dto.RoutineResponse;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.user.repository.UserRepository;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AiRoutineService {


    @Value("${gemini.project-id}")
    private String projectId;

    @Value("${gemini.location}")
    private String location;

    @Value("${gemini.model-name}")
    private String modelName;


    /**
     * 메서드의 두 번째 파라미터 타입을 List<String>에서 List<FlowerInfo>로 수정합니다.
     */
    public RoutineResponse generateRoutinesAndFlower(String userMood,
        List<FlowerInfo> flowerOptions) {

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {

            // 꽃 정보를 "id:꽃이름(꽃말)" 형식으로 AI에 전달
            String flowerData = flowerOptions.stream()
                .map(flower -> String.format("%d:%s(꽃말:%s)", flower.getId(), flower.getFlowerName(),
                    flower.getFlowerMeaning()))
                .collect(Collectors.joining(", "));

            String systemMessage = String.format("""
                당신은 사용자의 기분과 상황을 분석하고, 그에 맞는 루틴과 꽃을 추천하는 전문가입니다.
                다음 지시사항을 반드시 따라야 합니다:
                1. 사용자의 감정('%s')에 맞는 루틴 10개를 제안해야 합니다.
                2. 각 루틴은 공백 포함 20자 이내로 작성하고, 이모지를 앞에 붙이세요.
                [이모지 생성 규칙]
                단일 유니코드 문자로 구성된 기본 이모지만 사용. (예: 📚, 😀, 😎, ❤️, 🐶, 🌸, 🔥, 👍, 💖)
                성별, 인종, 직업 등이 결합된 복합 이모지(조합형/ZWJ 포함)는 절대 사용금지. (사용 금지 예: 🤷♀️, 👩💻, 👨👩👧👦, 👍🏽, 🚶‍♀️, 👨‍👩‍👧‍, 🏳️‍🌈👦, 👩‍💻, 🤝🏿, 👋🏻)
                오래된 시스템에서도 깨지지 않는 호환성이 좋은 이모지 위주로 골라야합니다."
                3. 제공된 꽃 목록과 꽃말을 분석하세요:
                   - 꽃 정보: [%s]
                4. 가장 어울리는 꽃 한 개를 선택하고, 반드시 'flowerId\\nrecommendedFlower' 형식으로 출력하세요.
                5. 최종 결과는 "루틴1\\n루틴2\\n...\\n루틴10\\n---꽃---\\n선택된꽃id\\n선택된꽃이름" 형식이어야 합니다.
                6. 다른 부가 설명 없이 요청된 형식으로 출력하세요.
                """, userMood, flowerData);

            Content systemInstruction = ContentMaker.fromMultiModalData(systemMessage);

            GenerativeModel model = new GenerativeModel(modelName, vertexAI)
                .withSystemInstruction(systemInstruction);

            // AI에게 content 생성 요청
            GenerateContentResponse response = model.generateContent(userMood);
            String rawResponse = ResponseHandler.getText(response);

            // 루틴과 꽃 분리
            String[] parts = rawResponse.split("---꽃---");
            if (parts.length < 2) {
                throw new IllegalStateException("API 응답이 예상된 형식이 아닙니다: " + rawResponse);
            }

            // 루틴 파싱
            List<String> routines = Arrays.stream(parts[0].trim().split("\\n"))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());

            // 꽃 정보 파싱: "선택된꽃id\n선택된꽃이름"
            String[] flowerLines = parts[1].trim().split("\\n");
            if (flowerLines.length < 2) {
                throw new IllegalStateException("꽃 정보 형식이 올바르지 않습니다: " + parts[1]);
            }

            Long flowerId;
            try {
                flowerId = Long.parseLong(flowerLines[0].trim());
            } catch (NumberFormatException e) {
                throw new IllegalStateException("꽃 ID를 숫자로 변환할 수 없습니다: " + flowerLines[0], e);
            }
            String recommendedFlower = flowerLines[1].trim();

            return new RoutineResponse(routines, flowerId, recommendedFlower, userMood);

        } catch (IOException e) {
            System.err.println("Gemini API 호출 실패!");
            throw new RuntimeException("Gemini API 호출 중 오류가 발생했습니다.", e);
        }
    }


}