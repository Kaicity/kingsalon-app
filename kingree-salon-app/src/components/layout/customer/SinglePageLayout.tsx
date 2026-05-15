import React from "react";

const SinglePageLayout = ({ children }: { children: React.ReactNode }) => {
  return <div className="px-6 py-28 md:px-20 min-h-screen">{children}</div>;
};

export default SinglePageLayout;
