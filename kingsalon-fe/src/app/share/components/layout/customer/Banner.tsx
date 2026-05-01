"use client";

import LocationOnIcon from "@mui/icons-material/LocationOn";
import SearchIcon from "@mui/icons-material/Search";
import { Autocomplete, Button, TextField } from "@mui/material";
import Header from "@/app/share/components/layout/customer/Header";

const topFilms = [{ label: "Hà Nội" }, { label: "TP. Hồ Chí Minh" }];

const Banner = () => {
  return (
    <div className="w-full relative h-[80vh]">
      <video
        className="w-full h-full object-cover"
        muted
        autoPlay
        // loop
        playsInline
        preload="auto"
        src="https://cdn.pixabay.com/video/2022/10/16/135154-761273535_medium.mp4"
      />
      <div className="textPart absolute flex flex-col items-center justify-center inset-0 text-white z-20 space-y-3 px-5">
        <h1 className="text-5xl font-bold">Hãy là chính mình</h1>
        <p className="text-slate-400 text-xl text-center font-semibold">
          Khám phá và tìm hiểu chăm sóc sắc đẹp, tuyệt vời cho bản thân
        </p>

        <div className="bg-white w-[95%] md:w-[800px] rounded-2xl md:rounded-full flex flex-col md:flex-row items-stretch md:items-center gap-3 py-3 px-4 shadow-lg">
          {/* Search */}
          <div className="flex items-center flex-1">
            <SearchIcon color="primary" className="mr-2" />
            <input
              type="text"
              placeholder="Tìm dịch vụ ?"
              className="flex-1 outline-none text-black bg-transparent"
            />
          </div>

          {/* Divider (desktop only) */}
          <div className="hidden md:block h-6 w-px bg-gray-200"></div>

          {/* Location */}
          <div className="flex items-center flex-1">
            <LocationOnIcon color="primary" className="mr-2" />

            <Autocomplete
              disablePortal
              options={topFilms}
              sx={{ flex: 1 }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  placeholder="Khu vực"
                  variant="outlined"
                  sx={{
                    "& .MuiOutlinedInput-root": {
                      borderRadius: "999px",

                      "& fieldset": {
                        border: "none",
                      },
                      "&:hover fieldset": {
                        border: "none",
                      },
                      "&.Mui-focused fieldset": {
                        border: "2px solid #D4AF37",
                      },
                    },
                  }}
                />
              )}
            />
          </div>

          {/* Button */}
          <Button
            variant="contained"
            color="primary"
            fullWidth
            sx={{
              borderRadius: "999px",
              textTransform: "none",
              px: 3,
              py: 1.5,
              width: { xs: "100%", md: "auto" },
            }}
          >
            Tìm kiếm
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Banner;
