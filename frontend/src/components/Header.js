import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { Tablet } from './../Responsive';

const Wrapper = styled.div`
  margin: 0;
  padding: 0;
  font-family: var(--primary-font);
  background: var(--secondary-color);
  color: var(--primary-color);

  nav {
    // box-shadow: 0 5px 5px #888888;
    background: white;

  }
 nav .nav-item .nav-link{
     color: var(--primary-color);
 }

  .navbar-brand {
    color: var(--primary-color); 
    font-size: 24px;
    font-family: 'Lora';
    font-style: italic;
    font-weight: 700;
    ${Tablet({ fontSize: "30px" })}
  }
  a:hover {
    color: #0a0f83;
    font-weight: 600;
  }
`;

function Header({ user }) {
    return (
        <Wrapper className="sticky-top">
            <nav className="navbar navbar-expand-sm sticky-top navbar-light bg-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">
                        <img
                            src="/images/brand.png"
                            alt=""
                            width="30"
                            height="24"
                            className="d-inline-block align-text-top me-2"
                        />
                        DaShop
                    </Link>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarTogglerDemo02"
                        aria-controls="navbarTogglerDemo02"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link" to="#home">
                                    Home
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="#contact">
                                    Contact
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="#about">
                                    About Us
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="#gallery">
                                    Gallery
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </Wrapper>
    );
}

export default Header;
