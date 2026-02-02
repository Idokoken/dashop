import React from "react";

function Rating(props) {
  const { rating, numReviews } = props;
  return (
    <div className="rating">
      <span>
        <i
          className={
            rating >= 1
              ? "fa fa-star"
              : rating >= 0.5
              ? "fa fa-star-half-stroke"
              : "fa-regular fa-star"
          }
        ></i>
      </span>
      <span>
        <i
          className={
            rating >= 1
              ? "fa fa-star"
              : rating >= 0.5
              ? "fa fa-star-half-stroke"
              : "fa-regular fa-star"
          }
        ></i>
      </span>
      <span>
        <i
          className={
            rating >= 1
              ? "fa fa-star"
              : rating >= 0.5
              ? "fa fa-star-half-stroke"
              : "fa-regular fa-star"
          }
        ></i>
      </span>
      <span>
        <i
          className={
            rating >= 1
              ? "fa fa-star"
              : rating >= 0.5
              ? "fa fa-star-half-stroke"
              : "fa-regular fa-star"
          }
        ></i>
      </span>
      <span>
        <i
          className={
            rating >= 1
              ? "fa fa-star"
              : rating >= 0.5
              ? "fa fa-star-half-stroke"
              : "fa-regular fa-star"
          }
        ></i>
      </span>
      <span className="ms-2">{numReviews + " reviews"}</span>
    </div>
  );
}

export default Rating;
