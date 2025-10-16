package D.Co.Harussak.plant.service;

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

@Service
public class AiRoutineService {

    @Value("${gemini.project-id}")
    private String projectId;

    @Value("${gemini.location}")
    private String location;

    @Value("${gemini.model-name}")
    private String modelName;

    public List<String> generateRoutines(String userMood) {
        // --- 디버깅용 로그는 그대로 유지합니다. ---
        System.out.println("==========================================================");
        System.out.println("==              Gemini API 호출 직전 최종 값 확인              ==");
        System.out.println("== Project ID: [" + projectId + "]");
        System.out.println("== Location:   [" + location + "]");
        System.out.println("== Model Name: [" + modelName + "]");
        System.out.println("==========================================================");

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            // ==========================================================
            //                 ★★★ 여기가 핵심 수정 부분입니다 ★★★
            // ==========================================================
            String systemMessage = """
                    당신은 사용자의 기분이나 상황에 맞춰 삶에 활력을 주는 "일반" 루틴을 추천해주는 라이프 코칭 전문가입니다.
                    다음 지시사항을 반드시 따라야 합니다:
                    1. 사용자의 감정을 이해하고, 긍정적인 에너지를 얻고 기분 전환에 도움이 될 만한 실용적인 루틴을 제안해야 합니다.
                    2. 추천하는 루틴은 '아침에 운동하기', '저녁 산책하기'처럼 일반적인 활동이어야 합니다.                   
                    3. 총 10개의 루틴을 생성해야 합니다.
                    4. 각 루틴은 짧은 단어의조합이어야합니다. 아침산책하기 30분산책하기 등 문장이 매우 간결해야합니다.
                    5. 결과는 오직 루틴 목록만 포함해야 하며, 다른 부가적인 설명이나 인사는 붙이지 마세요.
                    6. 각 루틴은 줄바꿈(newline)으로 구분해주세요. 예를 들어 "첫 번째 루틴\\n두 번째 루틴\\n..." 형식입니다.
                    7. 루틴은 매일매일 할수있는것이어야 합니다.
                    8. 기간을 설정해놓고 그 기간동안 매일 수행할만한 루틴을 알려주어야 합니다.
                    9.루틴의 길이는 20자이내여야합니다(공백포함).
                    10.이모지1개를 루틴의 맨 앞에 숫자 뒤에 놔줍니다.(루틴과 관련된 이모지),모든 루틴의 이모지는 겹치지 않아야합니다.
                    """;


            Content systemInstruction = ContentMaker.fromMultiModalData(systemMessage);

            GenerativeModel model = new GenerativeModel(modelName, vertexAI)
                    .withSystemInstruction(systemInstruction);

            GenerateContentResponse response = model.generateContent(userMood);
            String rawResponse = ResponseHandler.getText(response);

            // 응답 파싱 로직은 기존과 동일하게 유지합니다.
            return Arrays.stream(rawResponse.split("\\n"))
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

        } catch (IOException e) {
            System.err.println("Gemini API 호출 실패! 호출 시 사용된 값: projectId=" + projectId + ", location=" + location + ", modelName=" + modelName);
            throw new RuntimeException("Gemini API 호출 중 오류가 발생했습니다.", e);
        }
    }
}