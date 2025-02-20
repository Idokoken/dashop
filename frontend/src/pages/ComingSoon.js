import React from "react";
// import { Link } from "react-router-dom";
import styled from "styled-components";
import { Tablet } from "../Responsive";



const Wrapper = styled.div`
  min-height: 50vh;
  margin: 0;
  padding: 0;
  font-family: "Poppins", sans-serif;

  h1 {
    margin: 30px;
    font-family: "Spectral SC", serif;
    text-align: center;
  }
  
   a {
    padding: 10px 15px;
    background: var(--primary-color);
    color: white;
    font-size: 20px;
    font-weight: 500;
    border-radius: 30px;
    text-decoration: none;
  }
  .more a:hover {
    // color: #0a0f83;
    // font-weight: 700;
  }

  .soon{
   background-image: url("/images/comingsoon.jpg");
   background-size: contain;
   background-position: center;
   background-repeat: no-repeat;
   width: 100%;
   height: 50vh;
   display: flex;
   justify-content: center;
   align-items: flex-end;
   ${Tablet({ height: "60vh" })}
}

`;


const ComingSoon = () => {


  return (
    <Wrapper>
      <h1 className="mt-4">Welocome to Gemhandsglobal</h1>
      <div className="soon">

      </div>
    </Wrapper>
  );
};

export default ComingSoon;
