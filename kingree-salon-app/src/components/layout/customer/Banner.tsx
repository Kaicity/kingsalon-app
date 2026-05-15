"use client";

import { Button } from "@/components/ui/button";
import { MapPin, Search } from "lucide-react";
import { LocationSelect } from "./LocationSelect";

const locations = ["Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng"];

const Banner = () => {
  return (
    <div className="w-full relative h-[80vh]">
      {/* Video */}
      <video
        className="w-full h-full object-cover"
        muted
        autoPlay
        playsInline
        preload="auto"
        src="https://cdn.pixabay.com/video/2022/10/16/135154-761273535_medium.mp4"
      />

      {/* Overlay */}
      <div className="absolute inset-0 bg-black/40 z-10" />

      {/* Content */}
      <div className="absolute inset-0 flex flex-col items-center justify-center text-white z-20 space-y-4 px-5">
        <h4 className="font-semibold text-lg md:text-xl">
          Hãy tự tin về sắc đẹp bản thân
        </h4>

        <p className="text-sm text-center max-w-xl">
          Tìm hiểu chăm sóc sắc đẹp là một quá trình cho thấy bạn đang yêu bản
          thân mình một cách hoàn hảo
        </p>

        {/* Search box */}
        <div className="bg-white w-[95%] md:w-200 rounded-2xl md:rounded-full flex flex-col md:flex-row items-stretch md:items-center gap-3 py-3 px-4 shadow-lg mt-6">
          {/* Search input */}
          <div className="flex items-center flex-1 text-foreground">
            <Search className="mr-2" size={18} />
            <input
              type="text"
              placeholder="Tìm dịch vụ ?"
              className="flex-1 outline-none text-black bg-transparent"
            />
          </div>

          {/* Divider */}
          <div className="hidden md:block h-6 w-px bg-gray-200"></div>

          {/* Location select */}
          <div className="flex items-center flex-1">
            <MapPin className="mr-2 text-foreground" size={18} />
            <LocationSelect />
          </div>

          {/* Button */}
          <Button className="rounded-full px-6 py-2 w-full md:w-auto">
            Tìm kiếm
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Banner;
