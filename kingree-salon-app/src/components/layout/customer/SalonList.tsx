import SalonCard from "./SalonCard";

const SalonList = () => {
  return (
    <div className="flex flex-wrap items-center gap-4 md:gap-6">
      <SalonCard />
      <SalonCard />
      <SalonCard />
      <SalonCard />
      <SalonCard />
    </div>
  );
};

export default SalonList;
