"use client";

import Link from "next/link";
import { Typography } from "@mui/material";
import { Language, Instagram, YouTube } from "@mui/icons-material";

const Footer = () => {
  return (
    <footer className="bg-background border-t border-border mt-20">
      <div className="max-w-7xl mx-auto px-6 py-16 grid grid-cols-1 md:grid-cols-4 gap-10">
        {/* Brand */}
        <div>
          <Typography variant="h6" className="text-primary font-semibold mb-4">
            Aura Luxe
          </Typography>
          <p className="text-muted text-sm leading-relaxed">
            Curating the world's most exceptional beauty experiences for the
            modern luxury seeker.
          </p>
        </div>

        {/* Marketplace */}
        <div>
          <h4 className="text-foreground font-medium mb-4">MARKETPLACE</h4>
          <ul className="space-y-3 text-sm text-muted">
            <li>
              <Link href="#">Explore Salons</Link>
            </li>
            <li>
              <Link href="#">Premium Services</Link>
            </li>
            <li>
              <Link href="#">Gift Cards</Link>
            </li>
            <li>
              <Link href="#">Magazine</Link>
            </li>
          </ul>
        </div>

        {/* Partners */}
        <div>
          <h4 className="text-foreground font-medium mb-4">FOR PARTNERS</h4>
          <ul className="space-y-3 text-sm text-muted">
            <li>
              <Link href="#">Partner With Us</Link>
            </li>
            <li>
              <Link href="#">Salon Dashboard</Link>
            </li>
            <li>
              <Link href="#">Stylist Pro</Link>
            </li>
            <li>
              <Link href="#">Resource Hub</Link>
            </li>
          </ul>
        </div>

        {/* Support */}
        <div>
          <h4 className="text-foreground font-medium mb-4">SUPPORT</h4>
          <ul className="space-y-3 text-sm text-muted">
            <li>
              <Link href="#">Contact Support</Link>
            </li>
            <li>
              <Link href="#">Privacy Policy</Link>
            </li>
            <li>
              <Link href="#">Terms of Service</Link>
            </li>
          </ul>
        </div>
      </div>

      {/* Bottom */}
      <div className="border-t border-border">
        <div className="max-w-7xl mx-auto px-6 py-6 flex flex-col md:flex-row items-center justify-between gap-4 text-sm text-muted">
          <p>© 2024 Aura Luxe Beauty Marketplace. All rights reserved.</p>

          {/* Social */}
          <div className="flex items-center gap-4">
            <Language className="cursor-pointer hover:text-foreground" />
            <YouTube className="cursor-pointer hover:text-foreground" />
            <Instagram className="cursor-pointer hover:text-foreground" />
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
