// Main.java
import java.util.Scanner;

public class Main {
    private static Menu[] daftarMenu = {
        // Menu Makanan
        new Menu("Nasi Goreng", 25000, "Makanan"),
        new Menu("Nasi Padang", 35000, "Makanan"),
        new Menu("Mie Goreng", 20000, "Makanan"),
        new Menu("Sate Ayam", 30000, "Makanan"),
        
        // Menu Minuman
        new Menu("Es Teh", 5000, "Minuman"),
        new Menu("Es Jeruk", 8000, "Minuman"),
        new Menu("Es Kopi", 10000, "Minuman"),
        new Menu("Jus Alpukat", 15000, "Minuman")
    };

    public static void tampilkanMenu() {
        System.out.println("====== DAFTAR MENU RESTORAN ======");
        System.out.println("\nMAKANAN:");
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Makanan")) {
                System.out.printf("- %s (Rp %,d)\n", menu.getNama(), menu.getHarga());
            }
        }
        
        System.out.println("\nMINUMAN:");
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Minuman")) {
                System.out.printf("- %s (Rp %,d)\n", menu.getNama(), menu.getHarga());
            }
        }
        System.out.println("\n================================");
    }

    public static Menu cariMenu(String nama) {
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }

    public static void prosesStruk(String[] namaPesanan, int[] jumlahPesanan, int jumlahItem) {
        System.out.println("\n========= STRUK PEMBAYARAN =========");
        
        int totalHarga = 0;
        int jumlahMinuman = 0;
        
        System.out.println("Detail Pesanan:");
        for (int i = 0; i < jumlahItem; i++) {
            Menu menu = cariMenu(namaPesanan[i]);
            if (menu != null) {
                int hargaPerItem = menu.getHarga() * jumlahPesanan[i];
                System.out.printf("%s x%d: Rp %,d\n", menu.getNama(), jumlahPesanan[i], hargaPerItem);
                totalHarga += hargaPerItem;
                
                if (menu.getKategori().equals("Minuman")) {
                    jumlahMinuman += jumlahPesanan[i];
                }
            }
        }

        System.out.println("\nRincian Biaya:");
        System.out.printf("Total Pesanan: Rp %,d\n", totalHarga);
        
        int pajak = (int)(totalHarga * 0.1);
        int biayaLayanan = 20000;
        
        System.out.printf("Pajak (10%%): Rp %,d\n", pajak);
        System.out.printf("Biaya Pelayanan: Rp %,d\n", biayaLayanan);
        
        int totalSebelumDiskon = totalHarga + pajak + biayaLayanan;
        int totalAkhir = totalSebelumDiskon;
        
        if (totalHarga > 100000) {
            int diskon = (int)(totalHarga * 0.1);
            System.out.printf("\nDiskon 10%%: -Rp %,d\n", diskon);
            totalAkhir -= diskon;
        }
        
        if (totalHarga > 50000 && jumlahMinuman > 0) {
            System.out.println("Promo: Beli 1 Gratis 1 untuk minuman telah diterapkan!");
        }
        
        System.out.println("\n--------------------------------");
        System.out.printf("Total Pembayaran: Rp %,d\n", totalAkhir);
        System.out.println("================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tampilkanMenu();
        
        // Array untuk menyimpan pesanan (maksimal 4 menu)
        String[] pesanan = new String[4];
        int[] jumlah = new int[4];
        int jumlahItem = 0;
        
        System.out.println("\nMasukkan pesanan Anda (maksimal 4 menu)");
        System.out.println("Ketik 'selesai' untuk mengakhiri pesanan");
        
        while (jumlahItem < 4) {
            System.out.print("\nMasukkan nama menu (atau 'selesai'): ");
            String namaMenu = scanner.nextLine();
            
            if (namaMenu.equalsIgnoreCase("selesai")) {
                break;
            }
            
            Menu menu = cariMenu(namaMenu);
            if (menu == null) {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.");
                continue;
            }
            
            System.out.print("Masukkan jumlah: ");
            int jumlahPesanan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
            
            if (jumlahPesanan <= 0) {
                System.out.println("Jumlah pesanan tidak valid. Silakan coba lagi.");
                continue;
            }
            
            pesanan[jumlahItem] = namaMenu;
            jumlah[jumlahItem] = jumlahPesanan;
            jumlahItem++;
        }
        
        if (jumlahItem > 0) {
            prosesStruk(pesanan, jumlah, jumlahItem);
        } else {
            System.out.println("Tidak ada pesanan yang dimasukkan.");
        }
        
        scanner.close();
    }
}