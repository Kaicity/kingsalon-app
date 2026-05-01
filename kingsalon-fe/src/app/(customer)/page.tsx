import { servicesFakeData } from "../data/services_fakedata";
import Banner from "../share/components/layout/customer/Banner";
import HomeService from "../share/components/layout/customer/HomeService";

const HomePage = () => {
  return (
    <div className="space-y-20">
      <section>
        <Banner />
      </section>

      <section className="space-y-10 lg:space-y-0 lg:flex-col items-center gap-5 px-20">
        <div className="w-full lg:w-1/2">
          <h1 className="text-2xl font-semibold pb-9">
            Bạn đang tìm gì thế, bạn yêu ? 🥰
          </h1>
        </div>

        <div className="flex flex-wrap gap-5 items-center">
          {servicesFakeData.map((item, index) => (
            <HomeService
              id={item.id}
              image={item.image}
              name={item.name}
              key={index}
            />
          ))}
        </div>
      </section>
    </div>
  );
};

export default HomePage;
