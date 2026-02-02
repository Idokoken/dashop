import React from "react";
import Rating from "./Rating";
import { NavLink } from "react-router-dom";
import styled from "styled-components";
import { Tablet } from "../Responsive";
// import LoadingBox from "./LoadingBox";
// import MessageBox from "./MessageBox";
import { Data } from "../utils/Data";

const Wrapper = styled.div`
  min-height: 50vh;
  margin: 0;
  padding: 0;

  .row {
    display: grid;
    grid-template-columns: 50% 50%;
    gap: 5px;
    padding: 1rem;
    margin: 30px;
    ${Tablet({ gridTemplateColumns: "25% 25% 25% 25%" })}
  }

  .item {
    justify-content: center;
  }
  .card {
    border: 0.1rem #c0c0c0 solid;
    background-color: #f8f8f8;
    border-radius: 0.5rem;
    padding: 0;
  }
 
  .image-container {
    height: 150px;
    background-color: white;
    ${Tablet({ height: "200px" })}
  }
   img {
    border-radius: 0.5rem;
    width: 100%;
    height: 100%;
    object-fit: cover;  
  }
  .card-body {
    margin: 0;
    padding: 0 10px;
  }
  }
  h3 {
    font-size: 1.2rem;
    padding-top: 8px;
    padding-bottom: 8px;
    margin-bottom: 0;
    font-size: 16px;
    ${Tablet({ fontSize: "20px" })}
  }
  h4 {
    font-size: 1rem;
    padding: 0.5rem 0;
  }
  .price {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 8px 5px; 
    font-size: 12px;
    ${Tablet({ fontSize: "16px" })}
  }
  .price h4 {
    margin: 0;
    font-size: 12px;
    ${Tablet({ fontSize: "14px" })}
  }
  .add {
    background-color: rgba(000, 000, 000, 0.8) !important;
    color: white;
    padding: 4px 10px;
    font-size: 12px;
    ${Tablet({ fontSize: "16px" })}
  }
  .rating span {
    color: #f0c040;
    margin: 0.1rem 0;
    font-size: 0.8rem;
  }
  .rating span:last-child {
    color: #404040;
  }
  .content {
    margin: 0;
    padding: 20px;
  }
`;

function Products() {
  const items =
    Data &&
    Data.map((item, i) => {
      return (
        <div className="card" key={item._id}>
          <NavLink
            to={`/product/${item._id}`}
            style={{ textDecoration: "none", color: "rgba(30, 51, 187, 1)" }}
          >
            <div className="image-container">
              <img src={item.image} alt={item.name} />
            </div>
            <div className="card-body">
              <h3>{item.name}</h3>
            </div>
          </NavLink>
          <Rating rating={item.rating} numReviews={item.numReviews} />
          <div className="price my-2">
            <h4>${item.price}</h4>
            <div className="add btn">Add to Cart</div>
          </div>
        </div>
      );
    });

  return (
    <Wrapper>
      <div className="row">
        {
          //     loading?(
          //   <LoadingBox />
          // ) : error ? (
          // <MessageBox>{error}</MessageBox>
          // ) :

          <>{items}</>
        }
      </div>
      <div className="content mx-3">
        <h4>Others things</h4>
      </div>
    </Wrapper>
  );
}

export default Products;
