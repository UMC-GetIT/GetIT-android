package com.getit.getit.ui.main.home.recommend

import android.util.Log
import android.widget.Toast
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.home.data.RecommendQuestion


fun getQuestionSet(productKind: Int) : ArrayList<RecommendQuestion>{
    var recommendQuestions = ArrayList<RecommendQuestion>()

    when(productKind){
        1 -> {
            recommendQuestions.apply {
                add(
                    RecommendQuestion(
                        "주로 어떤 용도로 사용하시나요?",
                        arrayListOf("문서작성", "게이밍", "개발", "학습", "디자인", "기타")
                    )
                )
                add(
                    RecommendQuestion(
                        "예산이 어떻게 되시나요?",
                        arrayListOf("100만 원 이하","100만 원 이상 200만 원 이하", "200만 원 이상")
                    )
                )
                add(RecommendQuestion("직업이 무엇인가요?"
                    , arrayListOf("학생", "디자이너", "사무직", "개발자", "기타")))
            }
        }
        2 -> {
            recommendQuestions.apply {
                add(
                    RecommendQuestion(
                        "가장 중요하게 생각하는 요소가 무엇인가요?",
                        arrayListOf("성능", "깔끔한 디자인")
                    )
                )
                add(
                    RecommendQuestion(
                        "예산이 어떻게 되시나요?",
                        arrayListOf("100만 원 이하", "100만 원 이상")
                    )
                )
                add(RecommendQuestion("어떤 크기의 스크린을 원하시나요?",
                    arrayListOf("6.1인치 이하", "6.2인치 이상")))
            }
        }
        3 -> {
            recommendQuestions.apply {
                add(
                    RecommendQuestion(
                        "주로 어떤 용도로 사용하시나요?",
                        arrayListOf("영상시청", "필기", "드로잉/영상편집")
                    )
                )
                add(
                    RecommendQuestion(
                        "예산이 어떻게 되시나요?",
                        arrayListOf("80만 원 이하","80만 원 이상")
                    )
                )
                add(RecommendQuestion("어떤 크기의 스크린을 원하시나요?",
                    arrayListOf("10인치 이상", "11인치 이상")))
            }
        }
        4 -> {
            recommendQuestions.apply {
                add(
                    RecommendQuestion(
                        "가장 중요하게 생각하는 요소가 무엇인가요?",
                        arrayListOf("음질", "디자인", "블루투스")
                    )
                )
                add(
                    RecommendQuestion(
                        "예산이 어떻게 되시나요?",
                        arrayListOf("10만 원 이하","10만 원 이상")
                    )
                )
                add (RecommendQuestion(
                    "주로 어떤 용도로 사용하시나요?",
                    arrayListOf("전문용", "PC용", "텔레비전용")))
            }
        }
        5 -> {
            recommendQuestions.apply {
                add(
                    RecommendQuestion(
                        "어떤 용도로 사용하시나요?",
                        arrayListOf("문서작성", "게이밍", "개발", "학습", "디자인", "기타")
                    )
                )
                add(
                    RecommendQuestion(
                        "예산이 어떻게 되시나요?",
                        arrayListOf("100만 원 이하","100만 원 이상 200만 원 이하", "200만 원 이상")
                    )
                )
                add(RecommendQuestion("직업이 무엇인가요?", arrayListOf("학생", "디자이너", "사무직", "개발자", "기타")))
            }
        }
    }

    return recommendQuestions
}