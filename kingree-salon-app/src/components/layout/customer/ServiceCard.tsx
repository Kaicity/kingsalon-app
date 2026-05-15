import { Button } from "@/components/ui/button";
import { Clock, MapPin, Star } from "lucide-react";
import Image from "next/image";
import React from "react";

const ServiceCard = () => {
  return (
    <div className="w-full sm:w-[180px] md:w-[290px] rounded-4xl shadow-lg bg-white overflow-hidden">
      <div className="relative">
        <Image
          width={500}
          height={500}
          alt=""
          src="/images/salon_1.png"
          className="w-full h-56 object-cover"
        />

        <div className="absolute top-3 right-3 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-full flex items-center gap-1 shadow-md">
          <Star className="text-yellow-700 w-4 h-4 fill-yellow-700" />
          <h1 className="text-sm text-yellow-700 font-semibold">4.9</h1>
        </div>
      </div>

      <div className="p-4 pt-8">
        <div className="flex items-start justify-between gap-4 pb-4">
          <div className="space-y-2">
            <h1 className="text-lg font-semibold text-gray-900">
              Kingree Bến Thành
            </h1>

            <h3 className="text-sm text-gray-500">Khu trung tâm, TP.HCM</h3>

            <div className="flex items-center gap-4 text-sm text-gray-500">
              <div className="flex items-center gap-1 leading-none">
                <Clock className="w-4 h-4" />
                <span>180 phút</span>
              </div>

              <div className="flex items-center gap-1 leading-none">
                <MapPin className="w-4 h-4" />
                <span>2.5 km</span>
              </div>
            </div>
          </div>

          <div className="shrink-0">
            <h2 className="text-lg font-bold text-primary">499.000đ</h2>
          </div>
        </div>

        <Button
          variant="default"
          className="h-11 w-full rounded-3xl font-medium cursor-pointer"
        >
          Đặt Ngay
        </Button>
      </div>
    </div>
  );
};

export default ServiceCard;
