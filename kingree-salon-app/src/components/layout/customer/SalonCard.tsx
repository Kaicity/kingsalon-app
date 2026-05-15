"use client";

import { Button } from "@/components/ui/button";
import { Star, User } from "lucide-react";
import Image from "next/image";
import { useRouter } from "next/navigation";

const SalonCard = () => {
  const router = useRouter();

  return (
    <div className="w-full sm:w-[280px] md:w-[320px] rounded-4xl shadow-lg bg-white overflow-hidden">
      <Image
        width={500}
        height={500}
        alt=""
        src="/images/salon_1.png"
        className="w-full h-auto rounded-4xl object-cover pb-2"
      />

      <div className="p-5">
        <div className="flex items-start justify-between gap-3 pb-4">
          <div className="flex flex-col gap-2 justify-center items-start">
            <h1 className="text-lg font-semibold">Kingree Bến Thành</h1>
            <h3 className="text-md">Khu trung Tâm, TPHCM</h3>
          </div>

          <div className="flex items-center gap-1">
            <Star className="text-yellow-700 w-3 h-3" />
            <h1 className="text-lg text-yellow-700 font-semibold">4.9</h1>
          </div>
        </div>

        <div className="w-full flex flex-wrap gap-3 items-center text-text-tag pb-8">
          <div className="px-3 flex w-max h-8 rounded-full bg-accent/30 items-center justify-center">
            Nail
          </div>
          <div className="px-3 flex w-max h-8 rounded-full bg-accent/30 items-center justify-center">
            Massage
          </div>
          <div className="px-3 flex w-max h-8 rounded-full bg-accent/30 items-center justify-center">
            Cut hair
          </div>
        </div>

        <Button
          variant="outline"
          className="h-10 w-full rounded-3xl"
          onClick={() => router.push(`/services/salon/${1}`)}
        >
          Xem chi tiết
        </Button>
      </div>
    </div>
  );
};

export default SalonCard;
