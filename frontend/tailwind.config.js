/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}", // ✅ src 내부의 모든 JS/TS/JSX/TSX 파일 포함
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
