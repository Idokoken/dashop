// import { BASE_URL } from "./utils/API";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About"
import Contact from "./pages/Contact"
import Header from "./components/Header";
import ComingSoon from "./pages/ComingSoon";
import Footer from "./components/Footer";
import ErrorPage from "./pages/ErrorPage"
// import ScrollToTop from "./ScrollToTop";

function App() {
  return (
    <Router>

      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />

        <Route path="/soon" element={<ComingSoon />} />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
