from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import traceback
from predict import predict_from_file

app = FastAPI()


class PredictRequest(BaseModel):
    image_path: str


@app.get("/health")
def health():
    return {"status": "UP"}


@app.post("/predict")
def predict(request: PredictRequest):
    try:
        predicted_class, confidence = predict_from_file(request.image_path)

        return {
            "prediction": predicted_class,
            "confidence": confidence
        }

    except FileNotFoundError as e:
        raise HTTPException(status_code=503, detail=str(e))

    except Exception as e:
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=str(e))