package D.Co.Harussak.plant.service;

import D.Co.Harussak.plant.dto.FlowerInfo;
import D.Co.Harussak.plant.dto.RoutineResponse;
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
    public RoutineResponse generateRoutinesAndFlower(String userMood, List<FlowerInfo> flowerOptions) {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {

            String flowerData = flowerOptions.stream()
                    .map(flower -> String.format("%s (꽃말: %s)", flower.getFlowerName(), flower.getFlowerMeaning()))
                    .collect(Collectors.joining(", "));

            String systemMessage = String.format("""
                    당신은 사용자의 기분과 상황을 분석하고, 그에 맞는 루틴과 "꽃말"을 가진 꽃을 추천하는 라이프 코칭 전문가입니다.
                    다음 지시사항을 반드시 따라야 합니다:
                    1. 사용자의 감정('%s')을 깊이 이해하고, 긍정적 기운을 북돋아 줄 실용적인 루틴 "10개"를 제안해야 합니다.
                    2. 각 루틴은 '아침 산책하기', '좋아하는 음악 듣기'처럼 간결해야 합니다.
                    3. 아래 제공되는 5개의 꽃 목록과 각 꽃의 의미를 반드시 분석해야 합니다.
                       - 꽃 정보: [%s]
                    4. 사용자의 기분과 가장 잘 어울리는 "꽃말"을 가진 꽃을 목록에서 단 하나만 선택해야 합니다.
                    5. 최종 결과는 반드시 "루틴1\\n루틴2\\n...\\n루틴10\\n---꽃---\\n선택된꽃이름" 형식이어야 합니다.
                    6. 각루틴은 공백 포함하여 20자이내로 작성되어야 합니다.
                    7. 각 루틴에 맞는 1개의 이모지를 루틴의 맨 앞에 제공해야 합니다.
                    8. 각 루틴은 특정기간동안 매일 할수있는것이어야합니다. 
                    9 .다른 부가적인 설명이나 인사 없이, 오직 요청된 형식의 텍스트만 출력해야 합니다.
                    """, userMood, flowerData);

            Content systemInstruction = ContentMaker.fromMultiModalData(systemMessage);

            GenerativeModel model = new GenerativeModel(modelName, vertexAI)
                    .withSystemInstruction(systemInstruction);

            GenerateContentResponse response = model.generateContent(userMood);
            String rawResponse = ResponseHandler.getText(response);

            String[] parts = rawResponse.split("---꽃---");

            if (parts.length < 2) {
                throw new IllegalStateException("API 응답이 예상된 형식이 아닙니다: " + rawResponse);
            }

            List<String> routines = Arrays.stream(parts[0].trim().split("\\n"))
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.toList());

            String recommendedFlower = parts[1].trim();

            return new RoutineResponse(routines, recommendedFlower);

        } catch (IOException e) {
            System.err.println("Gemini API 호출 실패!");
            throw new RuntimeException("Gemini API 호출 중 오류가 발생했습니다.", e);
        }
    }
}