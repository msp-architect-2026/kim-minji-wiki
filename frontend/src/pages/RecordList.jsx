import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { fetchPagedRecords } from '../api/recordApi';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

export default function RecordList() {
  const [records, setRecords] = useState([]);
  const [search, setSearch] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);

  useEffect(() => {
    const load = async () => {
      try {
        const data = await fetchPagedRecords(search, page, 5);
        setRecords(data.content);
        setTotalPages(data.totalPages);
      } catch (e) {
        console.error('불러오기 실패', e);
      }
    };
    load();
  }, [search, page]);

  const handlePdfDownload = () => {
    const doc = new jsPDF();
    autoTable(doc, {
      head: [['ID', 'Filename', 'Prediction', 'Confidence', 'Created At']],
      body: records.map(r => [r.id, r.filename, r.prediction, r.confidence, r.createdAt])
    });
    doc.save('분석기록.pdf');
  };

  return (
    <div>
      <h1 className="text-3xl font-semibold text-gray-800 mb-6">Semiconductor Wafer Analysis Records</h1>

      <div className="mb-6 flex flex-col md:flex-row items-start md:items-center gap-4">
        <input
          type="text"
          placeholder="search"
          value={search}
          onChange={e => setSearch(e.target.value)}
          className="border border-gray-300 p-2 rounded-lg shadow-sm focus:ring focus:ring-blue-300 outline-none w-full md:w-auto"
        />
        <button
          onClick={handlePdfDownload}
          className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-colors"
        >
          Download Analysis List (PDF)
        </button>
      </div>

      <ul className="space-y-4">
        {records.map(record => (
          <li
            key={record.id}
            className="p-4 border rounded-xl shadow-md hover:shadow-lg transition-shadow duration-200"
          >
            <Link to={`/records/${record.id}`} className="text-blue-600 hover:underline text-lg font-medium">
              {record.filename} — {record.prediction}
            </Link>
            <p className="text-sm text-gray-500 mt-1">{record.createdAt}</p>
          </li>
        ))}
      </ul>

      <div className="flex justify-center items-center mt-8 gap-4">
        <button
          onClick={() => setPage(p => Math.max(0, p - 1))}
          disabled={page === 0}
          className="bg-gray-300 hover:bg-gray-400 text-black px-4 py-2 rounded disabled:opacity-50"
        >
          prev
        </button>
        <span className="text-sm font-medium">{page + 1} / {totalPages}</span>
        <button
          onClick={() => setPage(p => Math.min(totalPages - 1, p + 1))}
          disabled={page >= totalPages - 1}
          className="bg-gray-300 hover:bg-gray-400 text-black px-4 py-2 rounded disabled:opacity-50"
        >
          next
        </button>
      </div>
    </div>
  );
}
