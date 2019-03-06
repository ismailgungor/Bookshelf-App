package com.kekyazilim.bookshelf.util.fakes

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryFileManager
import org.json.JSONArray
import java.nio.charset.Charset

class FakeBookCategoryList {

    fun getFakeBookListasString(): String {
        return "[\n" +
                "  \"Aile (Kadın, Erkek ve Çocuk)  Kitapları\",\n" +
                "  \"Polisiye  Kitapları\",\n" +
                "  \"Anı-Mektup-Günlük  Kitapları\",\n" +
                "  \"Anlatı  Kitapları\",\n" +
                "  \"Antoloji  Kitapları\",\n" +
                "  \"Antropoloji-Etnoloji  Kitapları\",\n" +
                "  \"Araştırma-İnceleme  Kitapları\",\n" +
                "  \"Aşk  Kitapları\",\n" +
                "  \"Astroloji-Fal-Rüya Tabirleri  Kitapları\",\n" +
                "  \"Bilgisayar-İnternet  Kitapları\",\n" +
                "  \"Bilim-Kurgu  Kitapları\",\n" +
                "  \"Bilim-Teknoloji-Mühendislik  Kitapları\",\n" +
                "  \"Biyografi  Kitapları\",\n" +
                "  \"Çizgi-Roman  Kitapları\",\n" +
                "  \"Çocuk  Kitapları\",\n" +
                "  \"Deneme-İnceleme  Kitapları\",\n" +
                "  \"Dergi  Kitapları\",\n" +
                "  \"Ders Kitapları  Kitapları\",\n" +
                "  \"Diğer İnançlar  Kitapları\",\n" +
                "  \"Dilbilimi-Etimoloji  Kitapları\",\n" +
                "  \"Din (İslam)  Kitapları\",\n" +
                "  \"Dünya Klasikleri  Kitapları\",\n" +
                "  \"Edebiyat  Kitapları\",\n" +
                "  \"Efsaneler-Destanlar  Kitapları\",\n" +
                "  \"Eğitim  Kitapları\",\n" +
                "  \"Eğlence-Mizah  Kitapları\",\n" +
                "  \"Ekoloji  Kitapları\",\n" +
                "  \"Ekonomi-Emek-İş Dünyası  Kitapları\",\n" +
                "  \"Eleştiri-Kuram  Kitapları\",\n" +
                "  \"Fantastik  Kitapları\",\n" +
                "  \"Felsefe-Düşünce  Kitapları\",\n" +
                "  \"Gençlik  Kitapları\",\n" +
                "  \"Gezi  Kitapları\",\n" +
                "  \"Halk Edebiyatı  Kitapları\",\n" +
                "  \"Hikaye (Öykü)  Kitapları\",\n" +
                "  \"Hobi  Kitapları\",\n" +
                "  \"Hukuk  Kitapları\",\n" +
                "  \"İletişim-Medya  Kitapları\",\n" +
                "  \"İnsan ve Toplum  Kitapları\",\n" +
                "  \"Kadın  Kitapları\",\n" +
                "  \"Kadın-Erkek  Kitapları\",\n" +
                "  \"Kişisel Gelişim  Kitapları\",\n" +
                "  \"Korku-Gerilim  Kitapları\",\n" +
                "  \"Kültür  Kitapları\",\n" +
                "  \"Macera-Aksiyon  Kitapları\",\n" +
                "  \"Manga  Kitapları\",\n" +
                "  \"Masal  Kitapları\",\n" +
                "  \"Mitolojiler  Kitapları\",\n" +
                "  \"Moda  Kitapları\",\n" +
                "  \"Müzik  Kitapları\",\n" +
                "  \"Özlü Sözler-Duvar Yazıları  Kitapları\",\n" +
                "  \"Parapsikoloji-Spiritüalizm  Kitapları\",\n" +
                "  \"Psikoloji  Kitapları\",\n" +
                "  \"Roman  Kitapları\",\n" +
                "  \"Sağlık-Tıp  Kitapları\",\n" +
                "  \"Sanat  Kitapları\",\n" +
                "  \"Senaryo-Oyun  Kitapları\",\n" +
                "  \"Şiir  Kitapları\",\n" +
                "  \"Sinema  Kitapları\",\n" +
                "  \"Siyaset-Politika  Kitapları\",\n" +
                "  \"Sosyoloji  Kitapları\",\n" +
                "  \"Söyleşi-Röportaj  Kitapları\",\n" +
                "  \"Sözlük-Kılavuz Kitap-Ansiklopedi  Kitapları\",\n" +
                "  \"Spor  Kitapları\",\n" +
                "  \"Tarih  Kitapları\",\n" +
                "  \"Tasavvuf-Mezhepler-Tarikatlar  Kitapları\",\n" +
                "  \"Tiyatro  Kitapları\",\n" +
                "  \"Türk Klasikleri  Kitapları\",\n" +
                "  \"Yemek  Kitapları\",\n" +
                "  \"Yeraltı Edebiyatı  Kitapları\"\n" +
                "]"
    }

    fun provideBookListAsJsonArray(): List<String> {
        return GsonBuilder().create()
            .fromJson<List<String>>(
                this.getFakeBookListasString(),
                object : TypeToken<List<String>>() {}.type
            )
    }

}