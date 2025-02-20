import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { Tablet } from "../Responsive";


const Wrapper = styled.footer`
  padding: 0;
  margin: 0;
  font-family: var(--primary-font);
  background: var(--secondary-color);
  color: var(--primary-color);

  .footer {
    width: 100vw;
    display: flex;
    padding: 20px;
    flex-wrap: wrap;
    border-top: 4px solid var(--primary-color);
  }

  .footer .item {
    flex: 100%;
    ${Tablet({ flex: "33%" })}
    display: flex;
    flex-direction: column;
    ${Tablet({ alignItems: "center" })}
  }
  .footer .item .icon-header h3 {
    font-size: 30px;
    font-family: 'Lora';
    font-style: italic;
    font-weight: 700;
  }
  .footer .item h3 {
    color: var(--primary-color);
    font-weight: 500;
  }
  .rule {
    border-top: 4px solid var(--primary-color);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .copywite {
    margin: 10px;
    font-family: 'Lora';
    font-style: italic;
    ${Tablet({ fontSize: "20px" })}
  }
  .footer-logo{
    width: 30px;
    height: 30px;
  }
  .brand {
    align-self: flex-start;
  }
  .icon-header {
    display: flex;
  }
  .icons-container {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 10px;
  }
  .icon-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    width: 40px;
    border-radius: 5px;
  }
  .icon {
    margin-right: 20px;
    height: 100%;
    width: 100%;
  }
  a {
    text-decoration: none;
    color: inherit;
  }
  a:hover {
    color: #0a0f83;
    font-weight: 700;
  }
`;

function Footer() {
  return (
    <Wrapper>
      <div className="footer">
        <div className="item">
          <div className="icon-header">
            <img className="footer-logo" src="/images/brand.png" alt="brand" />{" "}
            <h3 className="ms-2">Dashop ltd</h3>
          </div>
          {/* <p>
            Empower, Protect, Prevail
          </p> */}
          <p>Empowerment, Protection, and the ability to overcome challenges.</p>
        </div>

        <div className="item">
          <h3>Quick Links</h3>
          <p>
            <Link to="/home">Home</Link>
          </p>
          <p>
            <Link to="/contact">Contact</Link>
          </p>
          <p>
            <Link to="/about">About Us</Link>
          </p>
          <p>
            <Link to="/gallery">Gallery</Link>
          </p>

        </div>
        <div className="item">
          <h3>Follow Us</h3>
          <div className="icons-container">

            <Link className="icon-container" to="https://facebook.com/raarsecurityltd">
              <img className="icon" src="/images/linkedin.png" alt="linkedIn" />
            </Link>
            <Link className="icon-container" to="https://youtube.com/raarsecurityltd">
              <img className="icon" src="/images/youtube.png" alt="youtube" />
            </Link>

          </div>
        </div>
      </div>
      <div className="rule">
        <p className="copywite">
          All Right reversed &copy; DaShop {new Date().getFullYear()}

        </p>
      </div>
    </Wrapper>
  );
}

export default Footer;
