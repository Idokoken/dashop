import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import { Tablet } from '../Responsive'

const Wrapper = styled.div`
    margin: 0;
    padding: 0;
    font-family: "Poppins", sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 50vh;
    background-image: url('/images/error1.jpg'); 
    background-size: cover;
    background-position: 100% 100%;
     ${Tablet({ minHeight: '70vh' })}

    
    .hero{
        background-color: rgba(0, 0 , 0, 0.4);
        padding: 20px;
        min-width: 80%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border-radius: 30px;
        ${Tablet({ minHeight: '40vh' })}
    }
    a{
        padding: 5px 10px;
        background: var(--primary-color);
        color: white;
        font-size: 20px;
        font-weight: 500;
        border-radius: 5px;
        text-decoration: none;
        border: 2px solid white;
    }
    h3{
        margin-bottom: 40px;
        color: white;
        font-weight: 600;
    }
`

function ErrorPage() {
    return (
        <Wrapper>
            <div className='hero'>
                <h3>Page Not Found</h3>
                <Link to="/" className='btn'>Go back home</Link>
            </div>
        </Wrapper>
    )
}

export default ErrorPage