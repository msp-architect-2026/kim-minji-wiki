import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { fetchRecordById } from '../api/recordApi';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

export default function RecordDetail() {
  const { id } = useParams();
  const [record, setRecord] = useState(null);

  useEffect(() => {
    const load = async () => {
      try {
        const data = await fetchRecordById(id);
        setRecord(data);
      } catch (e) {
        console.error('상세조회 실패', e);
      }
    };
    load();
  }, [id]);

  // ✅ 이미지 URL → base64 변환 함수
  const toDataUrl = (url) =>
    fetch(url)
      .then((res) => res.blob())
      .then(
        (blob) =>
          new Promise((resolve) => {
            const reader = new FileReader();
            reader.onloadend = () => resolve(reader.result);
            reader.readAsDataURL(blob);
          })
      );

  // ✅ PDF 다운로드 함수
  const handlePdfDownload = async () => {
    const doc = new jsPDF();

    doc.text('Analysis Details', 14, 20);

    autoTable(doc, {
      startY: 30,
      head: [['Field', 'Value']],
      body: [
        ['ID', record.id],
        ['Filename', record.filename],
        ['Prediction', record.prediction],
        ['Confidence', record.confidence],
        ['Created At', record.createdAt],
      ],
    });

    // 이미지 삽입
    try {
      const imgData = await toDataUrl(record.filepath);
      console.log('✅ Base64 image loaded:', imgData.slice(0, 50)); // 잘 변환됐는지 확인
      
      const pageWidth = doc.internal.pageSize.getWidth();
      const imageWidth = 80;
      const imageHeight = 60;
      const x = (pageWidth - imageWidth) / 2;
      const y = doc.lastAutoTable.finalY + 10;
    
      doc.addImage(imgData, 'PNG', x, y, imageWidth, imageHeight);
    } catch (err) {
      console.error('이미지 변환 실패:', err);
    }

    doc.save(`record-${record.id}.pdf`);
  };

  if (!record) return <div className="text-center text-gray-600">불러오는 중...</div>;

  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold text-gray-800">Analysis Details</h2>
      <p><strong>ID:</strong> {record.id}</p>
      <p><strong>Filename:</strong> {record.filename}</p>
      <p><strong>Prediction:</strong> {record.prediction}</p>
      <p><strong>Confidence:</strong> {record.confidence}</p>
      <p><strong>Created At:</strong> {record.createdAt}</p>

      <img
        src={record.filepath}
        alt="wafer image"
        className="w-full max-w-md border rounded-lg shadow"
      />

      <button
        onClick={handlePdfDownload}
        className="mt-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded transition"
      >
        Download PDF
      </button>
    </div>
  );
}
