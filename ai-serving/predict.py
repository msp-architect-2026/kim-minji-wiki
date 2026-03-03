from tensorflow.keras.models import load_model
import numpy as np
from PIL import Image

# 클래스 이름 정의
CLASS_NAMES = [
    "Center", "Donut", "Edge-Loc", "Edge-Ring", "Loc",
    "Near-full", "Random", "Scratch", "none"
]

# 모델 로드
model = load_model("wafer_defect_model.h5")

# 이미지 경로를 받아 예측 수행
def predict_from_file(image_path):
    # 이미지 로드 후 크기 변경 (26x26)
    image = Image.open(image_path).convert("RGB").resize((26, 26))
    image = np.array(image) / 255.0  # 정규화

    # 이미지 채널 수 맞추기 (1, 26, 26, 3) -> (1, 26, 26, 3)
    image = np.expand_dims(image, axis=0)  # 배치 차원 추가
    image = image.reshape(1, 26, 26, 3)

    preds = model.predict(image)
    label_index = np.argmax(preds)
    confidence = preds[0][label_index]

    # numpy.float32를 float로 변환
    return CLASS_NAMES[label_index], float(confidence)
