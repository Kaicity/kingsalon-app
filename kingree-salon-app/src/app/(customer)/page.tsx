import Banner from "@/components/layout/customer/Banner";
import HomeService from "@/components/layout/customer/HomeService";
import SalonList from "@/components/layout/customer/SalonList";
import { Button } from "@/components/ui/button";
import { servicesFakeData } from "@/data/services_fakedata";
import Image from "next/image";

const HomePage = () => {
  return (
    <div className="space-y-20">
      <section>
        <Banner />
      </section>

      <section className="px-6 md:px-20">
        <div className="w-full">
          <div className="pb-9">
            <h5 className="text-2xl font-semibold pb-2">
              Các dịch vụ Kingree Salon
            </h5>
            <p className="text-md text-primary">
              Mọi dịch vụ đều được thực hiện theo quy trình chuẩn quốc tế
            </p>
          </div>
          <div className="flex flex-wrap gap-3 items-center">
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
      </section>

      <section className="px-6 md:px-20 space-y-10 lg:space-y-0 lg:flex items-start gap-5">
        <div className="w-full grid gap-6 grid-col-2 md:grid-cols-3 grid-rows-12 h-[45vh] md:h-[90vh]">
          <div className="row-span-7 col-span-2 relative">
            <Image
              src="https://images.pexels.com/photos/31234756/pexels-photo-31234756.jpeg"
              alt=""
              fill
              priority
              className="object-cover rounded-3xl"
            />

            {/* Gradient overlay */}
            <div className="absolute inset-0 rounded-2xl bg-linear-to-t from-black/70 via-black/30 to-transparent" />

            {/* Content */}
            <div className="absolute bottom-5 left-5 text-white z-10">
              <div className="space-y-3">
                {/* <Chip label="Trending" color="primary" /> */}
                <h2 className="text-2xl font-bold">Combo Massage 16 bước</h2>
                <p className="text-sm opacity-90">
                  Mỗi bước sẽ là một trải nghiệm tuyệt vời dành cho bạn
                </p>
                <Button
                  variant="secondary"
                  className="py-3 px-2 w-45 bg-white rounded-3xl text-foreground"
                >
                  Đặt lịch ngay
                </Button>
              </div>
            </div>
          </div>

          <div className="row-span-6 md:row-span-5 col-span-1">
            <Image
              className="w-full h-full rounded-3xl object-cover"
              alt=""
              src="https://images.pexels.com/photos/19664877/pexels-photo-19664877.jpeg"
              width={500}
              height={500}
            />
          </div>

          <div className="row-span-6 md:row-span-5 col-span-1">
            <Image
              className="w-full h-full rounded-3xl object-cover"
              alt=""
              src="https://images.pexels.com/photos/5240820/pexels-photo-5240820.jpeg"
              width={500}
              height={500}
            />
          </div>
        </div>
      </section>

      <section className="px-6 md:px-20">
        <div className="text-center pb-10">
          <h1 className="text-2xl font-bold pb-2">
            Chuỗi Salon được ưa chuộng
          </h1>
          <p className="text-md text-primary">
            Mỗi Salon đều có một phong cách riêng hãy khám phá nhé
          </p>
        </div>

        <SalonList />
      </section>
    </div>
  );
};

export default HomePage;
