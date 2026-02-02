// import { BASE_URL } from "./utils/API";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import Contact from "./pages/Contact";
import Header from "./components/Header";
import ComingSoon from "./pages/ComingSoon";
import Footer from "./components/Footer";
import SingleProduct from "./pages/SingleProduct";
import ProductsPage from "./pages/ProductsPage";
import PrivacyPolicy from "./pages/PrivacyPolicy";
import TermsAndConditions from "./pages/TermsAndConditions";
import ErrorPage from "./pages/ErrorPage";
import Cart from "./pages/Cart";
import Register from "./pages/Register";
import Login from "./pages/Login";
// import ScrollToTop from "./ScrollToTop";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about-us" element={<About />} />
        <Route path="/contact-us" element={<Contact />} />

        <Route path="/products" element={<ProductsPage />} />
        <Route path="/product/:id" element={<SingleProduct />} />

        <Route path="/privacy-policy" element={<PrivacyPolicy />} />
        <Route path="/terms-and-conditions" element={<TermsAndConditions />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/signin" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/soon" element={<ComingSoon />} />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
