"use client";

import ServiceList from "@/components/layout/customer/ServiceList";
import SinglePageLayout from "@/components/layout/customer/SinglePageLayout";

import Title from "@/components/layout/customer/Title";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Separator } from "@/components/ui/separator";
import { Slider } from "@/components/ui/slider";
import { Switch } from "@/components/ui/switch";
import { RotateCcw, SlidersHorizontal } from "lucide-react";
import { useState } from "react";

const CATEGORIES = [
  "Hair",
  "Nails",
  "Skin Care",
  "Makeup",
  "Massage",
  "Brows & Lashes",
];

const RATINGS = [
  { value: "5", label: "5.0 sao" },
  { value: "4", label: "4.0 sao" },
  { value: "3", label: "3.0 sao" },
  { value: "2", label: "2.0 sao" },
  { value: "1", label: "1.0 sao" },
];

const ServicesPage = () => {
  const [selectedCategories, setSelectedCategories] = useState<string[]>([
    "Hair",
  ]);
  const [priceRange, setPriceRange] = useState<number[]>([300]);
  const [rating, setRating] = useState("4.5");
  const [instantBooking, setInstantBooking] = useState(false);

  const toggleCategory = (category: string) => {
    setSelectedCategories((prev) =>
      prev.includes(category)
        ? prev.filter((c) => c !== category)
        : [...prev, category],
    );
  };

  const handleReset = () => {
    setSelectedCategories(["Hair"]);
    setPriceRange([300]);
    setRating("4.5");
    setInstantBooking(false);
  };

  const formatPrice = (value: number) =>
    value >= 1000 ? "$1,000+" : `$${value}`;

  return (
    <SinglePageLayout>
      <Title
        title="Dịch Vụ Được Tuyển Chọn"
        subTitle="Khám phá các liệu trình làm đẹp được thiết kế riêng phù hợp với phong cách độc đáo của bạn.
           Từ tạo kiểu tóc thủ công đến các liệu pháp spa phục hồi."
      />

      <div className="w-full flex gap-6">
        {/* Categories Filter */}
        <div className="w-1/4">
          <div className="w-full max-w-sm bg-primary/5 rounded-2xl sticky top-26 left-0 right-0">
            {/* Header */}
            <div className="flex items-center justify-between px-5 pt-6 pb-4">
              <div className="flex items-center gap-2">
                <SlidersHorizontal className="w-4 h-4 text-primary" />
                <span className="text-sm font-semibold text-foreground tracking-wide uppercase">
                  Lọc theo
                </span>
              </div>
              <button
                onClick={handleReset}
                className="flex items-center gap-1 text-xs text-primary hover:text-[#8a5c4e] transition-colors"
              >
                <RotateCcw className="w-3 h-3" />
                Đặt lại
              </button>
            </div>

            <div className="px-5 space-y-6 pb-8">
              {/* Categories */}
              <section>
                <p className="text-xs font-semibold text-foreground uppercase tracking-widest mb-3">
                  Loại dịch vụ
                </p>
                <div className="flex flex-wrap gap-2">
                  {CATEGORIES.map((cat) => {
                    const active = selectedCategories.includes(cat);
                    return (
                      <button
                        key={cat}
                        onClick={() => toggleCategory(cat)}
                        className={`
                    px-3 py-1.5 rounded-full text-sm font-medium border transition-all duration-200
                    ${
                      active
                        ? "bg-primary text-secondary border-primary shadow-sm"
                        : "bg-white text-primary border-secondary hover:border-primary hover:bg-primary/10"
                    }
                  `}
                      >
                        {cat}
                      </button>
                    );
                  })}
                </div>
              </section>

              <Separator className="h-px" />

              {/* Price Range */}
              <section>
                <p className="text-xs font-semibold text-foreground uppercase tracking-widest mb-4">
                  Khoảng giá
                </p>
                <Slider
                  min={50}
                  max={1000}
                  step={50}
                  value={priceRange}
                  onValueChange={setPriceRange}
                  className="
    [&>span:first-child]:h-1
    [&>span:first-child]:bg-primary/20

  "
                />
                <div className="flex justify-between mt-2">
                  <span className="text-xs text-foreground">$50</span>
                  <span className="text-xs font-semibold text-primary">
                    {formatPrice(priceRange[0])}
                  </span>
                  <span className="text-xs text-foreground">$1,000+</span>
                </div>
              </section>

              <Separator className="h-px" />

              {/* Min Rating */}
              <section>
                <p className="text-xs font-semibold text-foreground uppercase tracking-widest mb-3">
                  Đánh giá
                </p>
                <RadioGroup
                  value={rating}
                  onValueChange={setRating}
                  className="space-y-2.5"
                >
                  {RATINGS.map((r) => (
                    <div key={r.value} className="flex items-center gap-3">
                      <RadioGroupItem
                        value={r.value}
                        id={`rating-${r.value}`}
                        className="border-primary text-foreground data-[state=checked]:border-primary data-[state=checked]:bg-primary"
                      />
                      <Label
                        htmlFor={`rating-${r.value}`}
                        className={`text-sm cursor-pointer transition-colors ${
                          rating === r.value
                            ? "text-foreground font-semibold"
                            : "text-foreground font-normal"
                        }`}
                      >
                        {Array.from({ length: Number(r.value) }).map(
                          (_, index) => (
                            <div className="">⭐</div>
                          ),
                        )}
                        {r.label}
                      </Label>
                    </div>
                  ))}
                </RadioGroup>
              </section>

              <Separator className="h-px" />

              {/* Instant Booking */}
              <section className="bg-white rounded-2xl p-4 border border-[#ede3de] shadow-sm">
                <div className="flex items-start justify-between gap-4">
                  <div className="space-y-1">
                    <p className="text-sm font-semibold text-[#2d1f1a]">
                      Instant Booking
                    </p>
                    <p className="text-xs text-foreground leading-snug">
                      Show only salons with immediate slots available for today.
                    </p>
                  </div>
                  <Switch
                    checked={instantBooking}
                    onCheckedChange={setInstantBooking}
                    className="data-[state=checked]:bg-[#c9895a] shrink-0 mt-0.5"
                  />
                </div>
                {instantBooking && (
                  <div className="mt-3 pt-3 border-t border-[#ede3de]">
                    <p className="text-xs text-[#c9895a] font-medium">
                      ✓ Showing salons with slots today
                    </p>
                  </div>
                )}
              </section>

              {/* Apply Button */}
              <Button className="w-full bg-primary text-white font-semibold rounded-xl h-11 text-sm tracking-wide shadow-md transition-all duration-200 hover:shadow-lg cursor-pointer">
                Áp Dụng
                {selectedCategories.length > 0 && (
                  <span className="ml-2 bg-white/25 text-white text-xs px-1.5 py-0.5 rounded-full">
                    {selectedCategories.length}
                  </span>
                )}
              </Button>
            </div>
          </div>
        </div>

        <div className="flex-1">
          <div className="w-full flex items-center justify-between mb-6">
            <p className="text-sm text-gray-500">
              Hiển thị 24 trên 128 dịch vụ
            </p>

            <div className="flex items-center gap-2">
              <p className="text-sm">Sắp xếp theo:</p>

              <Select>
                <SelectTrigger className="w-38 border-none">
                  <SelectValue placeholder="Mặc định" />
                </SelectTrigger>

                <SelectContent className="bg-accent-foreground">
                  <SelectGroup>
                    <SelectItem value="popular">Phổ biến</SelectItem>
                    <SelectItem value="price_low">Giá thấp đến cao</SelectItem>
                    <SelectItem value="price_high">Giá cao đến thấp</SelectItem>
                  </SelectGroup>
                </SelectContent>
              </Select>
            </div>
          </div>

          {/* List Services */}
          <ServiceList />
        </div>
      </div>
    </SinglePageLayout>
  );
};

export default ServicesPage;
