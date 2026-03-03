from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from predict import predict_from_file
import requests
import tempfile
import os

app = FastAPI()

class ImageUrlRequest(BaseModel):
    imageUrl: str

@app.post("/predict-image")
def predict_image_api(request: ImageUrlRequest):
    print("🔥 받은 S3 URL:", request.imageUrl)
    try:
        # 1. S3에서 이미지 다운로드
        response = requests.get(request.imageUrl)
        print("📥 응답 상태 코드:", response.status_code)
        print("📦 응답 Content-Type:", response.headers.get("Content-Type"))
        print("📏 응답 Content 길이:", len(response.content))

        if response.status_code != 200:
            raise HTTPException(status_code=400, detail="이미지를 불러올 수 없습니다.")

        # 2. 임시 파일 생성 후 저장
        with tempfile.NamedTemporaryFile(delete=False, suffix=".png") as temp_file:
            temp_file.write(response.content)
            temp_path = temp_file.name

        # 3. 예측 수행
        print("🧪 예측 시작:", temp_path)
        label, confidence = predict_from_file(temp_path)
        print("✅ 예측 완료:", label, confidence)

        # 4. 임시 파일 삭제
        os.remove(temp_path)

        return {"prediction": label, "confidence": round(confidence, 4)}

    except Exception as e:
        traceback.print_exc()  # ✅ 콘솔에 에러 전체 출력
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/")
def root():
    return {"message": "🎯 Wafer Defect Prediction API is running!"}
