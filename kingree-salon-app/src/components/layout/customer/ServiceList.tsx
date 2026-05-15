import React from "react";
import ServiceCard from "./ServiceCard";

const ServiceList = () => {
  return (
    <div className="w-full flex flex-wrap gap-5">
      {Array.from({ length: 50 }).map((_, index) => (
        <ServiceCard />
      ))}
    </div>
  );
};

export default ServiceList;
