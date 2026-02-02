import React from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import Rating from "./Rating";
import { Tablet } from "../Responsive";

import { Data } from "../utils/Data";

const Wrapper = styled.div`
  min-height: 50vh;
  font-family: var(--primary-font);
  background: var(--bg-color);

  .row {
    width: 100%;
    display: grid;
    grid-template-columns: 100%;
    gap: 5px;
    padding: 20px;
    padding-left: 10px;
    min-height: 60vh;
    ${Tablet({ gridTemplateColumns: "40% 60%", padding: "1rem" })}
  }
  .img-container {
    width: 100%;
    height: 250px;
  }

  img {
    width: 100%;
    height: 100%;
    border-radius: 10px;
  }

  .content {
    display: grid;
    grid-template-columns: 100%;
    gap: 10px;
    font-family: "Times New Roman", Times, serif;
    ${Tablet({ gridTemplateColumns: "60% 40%" })}
  }
  .desc h4 {
    color: rgba(30, 51, 187, 1);
  }
  .addtocart {
    background-color: rgba(000, 000, 000, 0.9);
    color: white;
    width: 90%;
    max-height: 200px;
    padding: 10px;
    border-radius: 10px;
    /* ${Tablet({ position: "absolute", right: "20px" })} */
  }
  button {
    background-color: yellow;
    width: 90%;
  }
  .rating,
  .addtocart p {
    margin-bottom: 6px;
  }

  .rating span {
    color: #f0c040;
    margin: 0.1rem 0;
    font-size: 0.8rem;
  }
  .rating span:last-child {
    color: #404040;
  }
  .status {
    width: 100%;
  }

  /* .relateditem {
    display: grid;
    grid-template-columns: 50% 50%;
    gap: 5px;
    margin: 1rem;
    ${Tablet({ gridTemplateColumns: "25% 25% 25% 25%" })}
  } */
`;

function Product(props) {
  const { id } = useParams();
  // const [qty, setQty] = useState(1);

  const product = Data.find((a, i) => a._id === id);

  // const handleAddToCart = () => {
  //   props.history.push(`/cart/${id}?qty={qty}`);
  // };

  return (
    <Wrapper>
      {/* { {loading ? (
        <LoadingBox></LoadingBox>
      ) : error ? (
        <MessageBox>{error}</MessageBox>
      ) : ( 
      product && ( */}
      <>
        <div className="row">
          <div className="img-container">
            <img src={product.image} alt={product.name} />
          </div>
          <div className="content">
            <div className="desc">
              <h4>Addidas Fit Shirt</h4>
              <Rating rating={product.rating} numReviews={product.numReviews} />
              <div className="price">
                <p>Price: ${product.price}</p>
                <p>Description: {product.description}</p>
              </div>
            </div>
            <div className="addtocart">
              <p>Seller</p>
              <p>{product.brand}</p>
              <Rating rating={product.rating} numReviews={product.numReviews} />
              <p>
                Price <span>${product.price}</span>
              </p>
              <p className="status">
                Status{" "}
                {product.countInStock > 0 ? (
                  <span className="text-success text-right">In Stock</span>
                ) : (
                  <span className="text-danger text-right">Unavailable</span>
                )}
              </p>
              <div className="qty">
                <div>Qty</div>
                <div>
                  {/* <select
                        value={qty}
                        onChange={(e) => setQty(e.target.value)}
                      >
                        {[...Array(product.countInStock.keys())].map((x) => (
                          <option key={x + 1} value={x + 1}>
                            {x + 1}
                          </option>
                        ))}
                      </select> */}
                </div>
              </div>
              {product.countInStock > 0 && (
                <>
                  <button>Add to Cart</button>
                </>
              )}
            </div>
          </div>
        </div>
      </>

      <div className="related container py-3">
        <h4>Related Products</h4>
        <div className="relateditem">Products</div>
      </div>
    </Wrapper>
  );
}

export default Product;
