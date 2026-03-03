import { useState } from 'react';
import { uploadAndAnalyze } from '../api/recordApi';

export default function UploadAndAnalyze() {
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [previewUrl, setPreviewUrl] = useState(null);

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
  setFile(selectedFile);
  setResult(null);
  setPreviewUrl(selectedFile ? URL.createObjectURL(selectedFile) : null);
};

  const handleUpload = async () => {
    if (!file) return;
    setLoading(true);
    try {
      const data = await uploadAndAnalyze(file);
      setResult(data);
    } catch (e) {
      console.error('분석 실패:', e);
      alert('분석에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  
  return (
  <div className="max-w-md mx-auto p-4 space-y-4">
    <h2 className="text-2xl font-bold text-gray-800">AI Analysis</h2>

    <input
      type="file"
      accept="image/*"
      onChange={handleFileChange}
      className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:border file:rounded file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
    />

    <button
      onClick={handleUpload}
      disabled={loading || !file}
      className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded disabled:opacity-50"
    >
      {loading ? '분석 중...' : 'Submit'}
    </button>

    {/* ✅ 이미지 미리보기 */}
    {previewUrl && (
      <div className="mt-4">
        <img
          src={previewUrl}
          alt="업로드된 이미지 미리보기"
          className="w-full max-w-md rounded border shadow"
        />
      </div>
    )}

    {/* ✅ 분석 결과 */}
    {result && (
      <div className="mt-6 border p-4 rounded shadow bg-white">
        <p><strong>Prediction:</strong> {result.prediction}</p>
        <p><strong>Confidence:</strong> {result.confidence}</p>
      </div>
    )}
  </div>
);

}
