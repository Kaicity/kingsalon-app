"use client";

import { cn } from "@/app/utils/clsx";
import { PersonOutlined, ShoppingBagOutlined } from "@mui/icons-material";
import { Button, Typography } from "@mui/material";
import Link from "next/link";
import { useEffect, useState } from "react";

const Header = () => {
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setScrolled(window.scrollY > 50);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  return (
    <header
      className={cn(
        "w-full sticky top-0 z-50 py-2 transition-all duration-300",
        scrolled && "bg-white shadow-md",
      )}
    >
      <div className="mx-auto px-6 h-16 flex items-center justify-between">
        {/* Nav */}
        <nav
          className={cn(
            "hidden md:flex items-center gap-8 text-sm font-medium",
            scrolled ? "text-black" : "text-white",
          )}
        >
          {/* Logo */}
          <Link href="/">
            <Typography
              variant="h6"
              className="text-xl font-bold cursor-pointer"
              color="primary"
            >
              Aura Luxe
            </Typography>
          </Link>

          <Link
            href="#"
            className={cn(
              "relative group",
              scrolled ? "" : "hover:text-primary",
            )}
          >
            Explore
            <span className="absolute left-0 -bottom-1 w-full h-[2px] bg-[#D4AF37]" />
          </Link>

          <Link
            href="#"
            className={cn("transition", scrolled ? "" : "hover:text-primary")}
          >
            Services
          </Link>

          <Link
            href="#"
            className={cn("transition", scrolled ? "" : "hover:text-primary")}
          >
            Salons
          </Link>

          <Link
            href="#"
            className={cn("transition", scrolled ? "" : "hover:text-primary")}
          >
            Stylists
          </Link>
        </nav>

        {/* Right actions */}
        <div className="flex items-center gap-4">
          <ShoppingBagOutlined
            className={cn(
              "w-5 h-5 cursor-pointer",
              scrolled ? "text-black" : "text-white",
            )}
          />
          <PersonOutlined
            className={cn(
              "w-5 h-5 cursor-pointer",
              scrolled ? "text-black" : "text-white",
            )}
          />

          <Button
            variant="contained"
            color="primary"
            sx={{ borderRadius: "999px", color: "white" }}
          >
            Đăng nhập
          </Button>
        </div>
      </div>
    </header>
  );
};

export default Header;
