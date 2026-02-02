import styled from "styled-components";
// import { tablet } from "../Responsive";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Product from "../components/Product";

const Wrapper = styled.div`
  min-height: 50vh;
  font-family: var(--primary-font);

  h3 {
    text-align: center;
    font-weight: 700;
  }
`;

function SingleProduct() {
  return (
    <>
      <Header />
      <Wrapper>
        <div className="container">
          <h3 className="my-4">Single Page</h3>
          <Product />
        </div>
      </Wrapper>
      <Footer />
    </>
  );
}

export default SingleProduct;
