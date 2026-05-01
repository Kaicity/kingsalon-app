import type { Service } from "@/app/data/services_fakedata";
import Image from "next/image";

const HomeService = (props: Service) => {
  const { id, image, name } = props;
  return (
    <div className="flex justify-center items-center flex-col gap-4 rounded-lg p-5 bg-slate-100 w-32 h-48">
      <Image
        className="w-20 h-20 rounded-full"
        alt=""
        src={image}
        width={500}
        height={300}
      />
      <h1 className="text-center">{name}</h1>
    </div>
  );
};

export default HomeService;
