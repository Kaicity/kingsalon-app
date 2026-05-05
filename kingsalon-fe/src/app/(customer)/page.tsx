import Image from "next/image";
import { servicesFakeData } from "../data/services_fakedata";
import Banner from "../share/components/layout/customer/Banner";
import HomeService from "../share/components/layout/customer/HomeService";
import Header from "../share/components/layout/customer/Header";

const HomePage = () => {
  return (
    <div className="space-y-20">
      <section>
        <div className="relative">
          <Header />
          <Banner />
        </div>
      </section>

      <section className="space-y-10 lg:space-y-0 lg:flex items-start gap-5 px-20">
        <div className="w-full lg:w-1/2">
          <h1 className="text-2xl font-semibold pb-9">
            Bạn đang tìm gì thế, bạn yêu ? 🥰
          </h1>
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
        </div>

        <div className="w-full lg:w-1/2 grid gap-3 grid-cols-2 grid-rows-12 h-[45vh] md:h-[90vh]">
          <div className="row-span-7">
            <Image
              className="w-full h-full rounded-md object-cover"
              alt=""
              src="https://images.pexels.com/photos/32329615/pexels-photo-32329615.jpeg"
              width={500}
              height={500}
            />
          </div>

          <div className="row-span-5">
            <Image
              className="w-full h-full rounded-md object-cover"
              alt=""
              src="https://images.pexels.com/photos/17665771/pexels-photo-17665771.jpeg"
              width={500}
              height={500}
            />
          </div>

          <div className="row-span-7">
            <Image
              className="w-full h-full rounded-md object-cover"
              alt=""
              src="https://images.pexels.com/photos/5240820/pexels-photo-5240820.jpeg"
              width={500}
              height={500}
            />
          </div>

          <div className="row-span-5">
            <Image
              className="w-full h-full rounded-md object-cover"
              alt=""
              src="https://images.pexels.com/photos/7755238/pexels-photo-7755238.jpeg"
              width={500}
              height={500}
            />
          </div>
        </div>
      </section>

      <section className="px-20">
        <h1 className="text-3xl font-bold pb-10">
          Đặt Salon mà bạn yêu thích gần bạn
        </h1>

        <SalonLis
      </section>
    </div>
  );
};

export default HomePage;
