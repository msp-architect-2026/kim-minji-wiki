import { Routes, Route } from 'react-router-dom';
import RecordList from './pages/RecordList';
import RecordDetail from './pages/RecordDetail';
import UploadAndAnalyze from './pages/UploadAndAnalyze';

<Route path="/analyze" element={<UploadAndAnalyze />} />

import { Link } from 'react-router-dom';

function App() {
  return (
    <div className="max-w-screen-lg mx-auto p-8">
      <nav className="mb-6 flex gap-4 text-blue-600">
        <Link to="/">Analysis History</Link>
        <Link to="/analyze">AI Analysis</Link>
      </nav>

      <Routes>
        <Route path="/" element={<RecordList />} />
        <Route path="/records/:id" element={<RecordDetail />} />
        <Route path="/analyze" element={<UploadAndAnalyze />} />
      </Routes>
    </div>
  );
}


export default App;
