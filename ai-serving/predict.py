import os
import numpy as np
from tensorflow.keras.models import load_model
from PIL import Image

MODEL_PATH = os.getenv("MODEL_PATH", "wafer_defect_model.h5")

model = None


def get_model():
    global model

    if model is None:
        if not os.path.exists(MODEL_PATH):
            raise FileNotFoundError(f"Model file not found: {MODEL_PATH}")

        model = load_model(MODEL_PATH)

    return model


def preprocess_image(image_path):
    img = Image.open(image_path).convert("L")
    img = img.resize((28, 28))
    img_array = np.array(img) / 255.0
    img_array = img_array.reshape(1, 28, 28, 1)
    return img_array


def predict_from_file(image_path):
    model_instance = get_model()

    img_array = preprocess_image(image_path)
    predictions = model_instance.predict(img_array)

    predicted_class = int(np.argmax(predictions))
    confidence = float(np.max(predictions))

    return predicted_class, confidence