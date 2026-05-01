import Header from "../share/components/layout/customer/Header";
import Footer from "../share/components/layout/customer/Footer";

export default function CustomerLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div className="absolute inset-0">
        <Header />
      </div>
      <main className="flex-1">{children}</main>
      <Footer />
    </>
  );
}
