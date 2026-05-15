import type { Service } from "@/data/services_fakedata";
import Image from "next/image";

const HomeService = (props: Service) => {
  const { id, image, name } = props;
  return (
    <div className="flex justify-center items-center flex-col gap-4 rounded-lg p-5 w-30 h-42 bg-slate-50 cursor-pointer hover:bg-slate-100 shadow-sm">
      <Image
        className="w-16 h-16 rounded-full"
        alt=""
        src={image}
        width={500}
        height={300}
      />
      <h1 className="text-center text-sm opacity-90">{name}</h1>
    </div>
  );
};

export default HomeService;
