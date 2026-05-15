"use client";

import { useState } from "react";
import Image from "next/image";
import { Star, Clock, Plus, ChevronRight } from "lucide-react";
import SinglePageLayout from "@/components/layout/customer/SinglePageLayout";
import { Button } from "@/components/ui/button";

const galleryImages = [
  {
    src: "https://images.pexels.com/photos/260922/pexels-photo-260922.jpeg?w=800",
    alt: "Salon interior",
    className: "col-span-2 row-span-2",
  },
  {
    src: "https://images.pexels.com/photos/3993449/pexels-photo-3993449.jpeg?w=400",
    alt: "Hair styling",
    className: "col-span-1 row-span-1",
  },
  {
    src: "https://images.pexels.com/photos/1633525/pexels-photo-1633525.jpeg?w=400",
    alt: "Outdoor",
    className: "col-span-2 row-span-1",
  },
  {
    src: "https://images.pexels.com/photos/3997394/pexels-photo-3997394.jpeg?w=400",
    alt: "Nails",
    className: "col-span-1 row-span-1",
  },
  {
    src: "https://images.pexels.com/photos/3865557/pexels-photo-3865557.jpeg?w=400",
    alt: "Ambiance",
    className: "col-span-1 row-span-1",
  },
  {
    src: "https://images.pexels.com/photos/3997394/pexels-photo-3997394.jpeg?w=400",
    alt: "Nails",
    className: "col-span-1 row-span-1",
  },
];

const serviceCategories = [
  {
    label: "Hair Artistry",
    icon: "✂",
    services: [
      {
        id: 1,
        name: "Signature Couture Cut",
        desc: "Precision haircut with consultation, blow-dry and style finishing.",
        duration: "45 min",
        price: 630000,
      },
      {
        id: 2,
        name: "Balayage Lumière",
        desc: "Sun-kissed highlights hand-painted for a natural, radiant dimension.",
        duration: "90 min",
        price: 2100000,
      },
    ],
  },
  {
    label: "Nail Atelier",
    icon: "💅",
    services: [
      {
        id: 3,
        name: "Ritual Manicure",
        desc: "Premium nail care with cuticle treatment, exfoliation, and luxury massage.",
        duration: "45 min",
        price: 380000,
      },
    ],
  },
];

const artisans = [
  {
    name: "Ariana K.",
    role: "Creative Director",
    avatar:
      "https://images.pexels.com/photos/21006299/pexels-photo-21006299.jpeg",
  },
  {
    name: "Marcus L.",
    role: "Co-Artisan",
    avatar:
      "https://images.pexels.com/photos/21006299/pexels-photo-21006299.jpeg",
  },
  {
    name: "Stephanie K.",
    role: "Nail Artist",
    avatar:
      "https://images.pexels.com/photos/21006299/pexels-photo-21006299.jpeg",
  },
  {
    name: "Octavia C.",
    role: "Stylist Expert",
    avatar:
      "https://images.pexels.com/photos/21006299/pexels-photo-21006299.jpeg",
  },
];

type CartItem = {
  id: number;
  name: string;
  price: number;
  originalPrice?: number;
};

const TABS = ["Services", "Artisan", "Reviews"] as const;
type Tab = (typeof TABS)[number];

const formatPrice = (price: number) =>
  new Intl.NumberFormat("vi-VN").format(price) + "₫";

const SalonProfilePage = () => {
  const [activeTab, setActiveTab] = useState<Tab>("Services");

  const [cart, setCart] = useState<CartItem[]>([
    { id: 1, name: "Signature Couture Cut", price: 630000 },
    { id: 3, name: "Ritual Manicure", price: 380000, originalPrice: 420000 },
  ]);

  const addToCart = (service: { id: number; name: string; price: number }) => {
    if (!cart.find((item) => item.id === service.id)) {
      setCart((prev) => [...prev, service]);
    }
  };

  const totalPrice = cart.reduce((sum, item) => sum + item.price, 0);

  return (
    <SinglePageLayout>
      <div className="space-y-6">
        {/* Gallery */}
        <div className="grid grid-cols-5 grid-rows-2 gap-2 h-[440px] rounded-2xl overflow-hidden">
          {galleryImages.map((img, i) => (
            <div key={i} className={`relative ${img.className}`}>
              <Image
                src={img.src}
                alt={img.alt}
                fill
                className="object-cover"
                sizes="(max-width: 768px) 100vw, 50vw"
              />
            </div>
          ))}
        </div>

        {/* Two-column layout */}
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_300px] gap-8 items-start">
          {/* Left column */}
          <div className="min-w-0 space-y-5">
            {/* Info */}
            <div className="space-y-1.5">
              <h1 className="text-3xl font-bold tracking-tight">
                Kingree Bến Thành
              </h1>
              <div className="flex items-center gap-2">
                <Star className="fill-primary text-primary size-4" />
                <span className="font-bold text-primary text-sm">5.0</span>
                <span className="text-slate-400 text-sm">
                  (124 lượt đánh giá)
                </span>
              </div>
              <p className="text-slate-500 text-sm">
                Khu trung tâm Quận 1, TP.HCM
              </p>
            </div>

            {/* Tabs */}
            <div className="flex border-b border-slate-200 gap-1">
              {TABS.map((tab) => (
                <button
                  key={tab}
                  onClick={() => setActiveTab(tab)}
                  className={`pb-3 px-4 text-sm font-medium border-b-2 transition-colors whitespace-nowrap -mb-px ${
                    activeTab === tab
                      ? "border-slate-900 text-slate-900"
                      : "border-transparent text-slate-400 hover:text-slate-600"
                  }`}
                >
                  {tab}
                </button>
              ))}
            </div>

            {/* Tab Content */}
            {activeTab === "Services" && (
              <div className="space-y-2">
                {serviceCategories.map((category) => (
                  <div key={category.label}>
                    <div className="flex items-center gap-2 text-xs font-semibold text-slate-400 uppercase tracking-widest mb-3 mt-4">
                      <span>{category.icon}</span>
                      <span>{category.label}</span>
                    </div>
                    <div className="divide-y divide-slate-100">
                      {category.services.map((service) => (
                        <div
                          key={service.id}
                          className="flex items-center justify-between py-4 gap-4"
                        >
                          <div className="flex-1 min-w-0 space-y-1">
                            <p className="font-semibold text-sm text-slate-900">
                              {service.name}
                            </p>
                            <p className="text-xs text-slate-400 leading-relaxed">
                              {service.desc}
                            </p>
                            <div className="flex items-center gap-4 text-xs text-slate-400">
                              <span className="flex items-center gap-1">
                                <Clock className="size-3" />
                                {service.duration}
                              </span>
                              <span className="font-semibold text-primary">
                                {formatPrice(service.price)}
                              </span>
                            </div>
                          </div>
                          <Button
                            onClick={() => addToCart(service)}
                            disabled={!!cart.find((c) => c.id === service.id)}
                            className={`w-max rounded-full ${
                              cart.find((c) => c.id === service.id)
                                ? "bg-primary text-white cursor-not-allowed opacity-60"
                                : "border-primary bg-white text-primary cursor-pointer hover:bg-transparent"
                            }`}
                          >
                            Thêm
                          </Button>
                        </div>
                      ))}
                    </div>
                  </div>
                ))}

                {/* Meet the Artisans */}
                <div className="pt-8 border-t border-slate-100 mt-4">
                  <h2 className="text-lg font-bold text-center mb-6">
                    Meet the Artisans
                  </h2>
                  <div className="grid grid-cols-4 gap-4">
                    {artisans.map((artisan) => (
                      <div key={artisan.name} className="text-center space-y-2">
                        <div className="relative w-16 h-16 mx-auto rounded-full overflow-hidden border-2 border-primary">
                          <Image
                            src={artisan.avatar}
                            alt={artisan.name}
                            fill
                            className="object-cover"
                            sizes="64px"
                          />
                        </div>
                        <div>
                          <p className="text-sm font-semibold text-slate-800">
                            {artisan.name}
                          </p>
                          <p className="text-[10px] text-slate-400 uppercase tracking-wide">
                            {artisan.role}
                          </p>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            )}

            {activeTab === "Artisan" && (
              <div className="grid grid-cols-2 gap-4 pt-2">
                {artisans.map((artisan) => (
                  <div
                    key={artisan.name}
                    className="flex items-center gap-3 p-4 rounded-2xl border border-slate-100 hover:border-primary transition-colors"
                  >
                    <div className="relative w-14 h-14 rounded-full overflow-hidden flex-shrink-0">
                      <Image
                        src={artisan.avatar}
                        alt={artisan.name}
                        fill
                        className="object-cover"
                        sizes="56px"
                      />
                    </div>
                    <div>
                      <p className="font-semibold text-sm">{artisan.name}</p>
                      <p className="text-xs text-slate-400">{artisan.role}</p>
                    </div>
                  </div>
                ))}
              </div>
            )}

            {activeTab === "Reviews" && (
              <div className="pt-2 text-slate-400 text-sm">
                Chưa có reviews nào.
              </div>
            )}
          </div>

          {/* Booking Sidebar */}
          <div className="bg-white border border-slate-200 rounded-2xl p-5 sticky top-24 space-y-4">
            <h3 className="font-semibold text-sm text-slate-800">
              Your Appointment
            </h3>

            <div className="space-y-3">
              {cart.map((item) => (
                <div
                  key={item.id}
                  className="flex justify-between items-start gap-2"
                >
                  <div className="min-w-0">
                    <p className="text-sm font-medium text-slate-800 leading-tight">
                      {item.name}
                    </p>
                    {item.originalPrice && (
                      <p className="text-xs text-slate-400">Taxes & Fees</p>
                    )}
                  </div>
                  <div className="text-right flex-shrink-0">
                    <p className="text-sm font-semibold">
                      {formatPrice(item.price)}
                    </p>
                    {item.originalPrice && (
                      <p className="text-xs line-through text-slate-400">
                        {formatPrice(item.originalPrice)}
                      </p>
                    )}
                  </div>
                </div>
              ))}
            </div>

            <div className="border-t border-slate-100 pt-3 flex justify-between items-center">
              <span className="text-sm text-slate-500">Total Estimate</span>
              <span className="text-2xl font-bold">
                {formatPrice(totalPrice)}
              </span>
            </div>

            <Button className="w-full bg-primary hover:bg-primary text-white text-sm font-semibold py-6 px-4 rounded-xl flex items-center justify-center gap-2 transition-colors cursor-pointer">
              BOOK APPOINTMENT
              <ChevronRight className="size-4" />
            </Button>

            <p className="text-[11px] text-slate-400 text-center leading-relaxed">
              Free cancellation up to 24 hours before your appointment. No card
              required to book.
            </p>
          </div>
        </div>
      </div>
    </SinglePageLayout>
  );
};

export default SalonProfilePage;
