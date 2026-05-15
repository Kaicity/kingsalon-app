export default function SellerLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex h-full">
      <div className="flex-1 flex flex-col">
        <main className="p-4 flex-1 overflow-auto">{children}</main>
      </div>
    </div>
  );
}
