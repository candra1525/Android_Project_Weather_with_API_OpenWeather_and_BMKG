package com.mdp.Weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity{

    // Declare Variables
    ListView list;
    SearchView searchView;
    String[] countryNameList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Generate sample data
        countryNameList = new String[]{
        "afghanistan, Kabul",
        "albania, Tirana",
        "algeria, Algiers",
        "american samoa, Pago Pago",
        "andorra, Andorra la Vella",
        "angola, Luanda",
        "anguilla, The Valley",
        "antigua and barbuda, Saint John's",
        "argentina, Buenos Aires",
        "armenia, Yerevan",
        "aruba, Oranjestad",
        "australia, Canberra",
        "austria, Vienna",
        "azerbaijan, Baku",
        "bahamas, Nassau",
        "bahrain, Manama",
        "bangladesh, Dhaka",
        "barbados, Bridgetown",
        "belarus, Minsk",
        "belgium, Brussels",
        "belize, Belmopan",
        "benin, Porto-Novo",
        "bermuda, Hamilton",
        "bhutan, Thimphu",
        "bolivia, La Paz",
        "bosnia and herzegovina, Sarajevo",
        "botswana, Gaborone",
        "brazil, Brasilia",
        "british indian ocean territory, Diego Garcia",
        "british virgin islands, Road Town",
        "brunei, Bandar Seri Begawan",
        "bulgaria, Sofia",
        "burkina faso, Ouagadougou",
        "burma, Naypyidaw",
        "burundi, Bujumbura",
        "cambodia, Phnom Penh",
        "cameroon, Yaounde",
        "canada, Ottawa",
        "cape verde, Praia",
        "cayman islands, George Town",
        "central african republic, Bangui",
        "chad, N'Djamena",
        "chile, Santiago",
        "china, Beijing",
        "colombia, Bogota",
        "comoros, Moroni",
        "congo democratic republic, Kinshasa",
        "congo republic, Brazzaville",
        "cook islands, Avarua",
        "costa rica, San Jose",
        "cote divoire, Yamoussoukro",
        "croatia, Zagreb",
        "cuba, Havana",
        "cyprus, Nicosia",
        "czech republic, Prague",
        "denmark, Copenhagen",
        "djibouti, Djibouti",
        "dominica, Roseau",
        "dominican republic, Santo Domingo",
        "east timor, Dili",
        "egypt, Cairo",
        "el salvador, San Salvador",
        "england, London",
        "equador, Quito",
        "equatorial guinea, Malabo",
        "eritrea, Asmara",
        "estonia, Tallinn",
        "ethiopia, Addis Ababa",
        "falkland islands, Stanley",
        "faroe islands, Torshavn",
        "fiji, Suva",
        "finland, Helsinki",
        "france, Paris",
        "french polynesia, Papeete",
        "gabon, Libreville",
        "gambia, Banjul",
        "georgia, Tbilisi",
        "germany, Berlin",
        "ghana, Accra",
        "gibraltar, Gibraltar",
        "great britain, Athens",
        "greece, Nuuk",
        "greenland, Saint George's",
        "grenada, Hagatna",
        "guam, Guatemala City",
        "guatemala, Saint Peter Port",
        "guernsey, Conakry",
        "guinea, Bissau",
        "guinea bissau, Georgetown",
        "guyana, Port-au-Prince",
        "haiti, Port-au-Prince",
        "honduras, Tegucigalpa",
        "hong kong, Hong Kong",
        "hungary, Budapest",
        "iceland, Reykjavik",
        "india, New Delhi",
        "indonesia, Jakarta",
                "indonesia, Palembang",
                "indonesia, Banda Aceh",
                "indonesia, Langsa",
                "indonesia, Lhokseumawe",
                "indonesia, Sabang",
                "indonesia, Subulussalam",
                "indonesia, Binjai",
                "indonesia, Gunungsitoli",
                "indonesia, Medan",
                "indonesia, Padang Sidempuan",
                "indonesia, Pematangsiantar",
                "indonesia, Sibolga",
                "indonesia, Tanjung Balai",
                "indonesia, Tebing Tinggi",
                "indonesia, Lubuk Linggau",
                "indonesia, Pagar Alam",
                "indonesia, Prabumulih",
                "indonesia, Sekayu",
                "indonesia, Bukit Tinggi",
                "indonesia, Padang",
                "indonesia, Padang Panjang",
                "indonesia, Pariaman",
                "indonesia, Payakumbuh",
                "indonesia, Sawahlunto",
                "indonesia, Solok",
                "indonesia, Sungai Penuh",
                "indonesia, Jambi",
                "indonesia, Bandung",
                "indonesia, Bekasi",
                "indonesia, Bogor",
                "indonesia, Cimahi",
                "indonesia, Cirebon",
                "indonesia, Depok",
                "indonesia, Sukabumi",
                "indonesia, Tasikmalaya",
                "indonesia, Banjar",
                "indonesia, Magelang",
                "indonesia, Pekalongan",
                "indonesia, Salatiga Semarang",
                "indonesia, Surakarta",
                "indonesia, Tegal",
                "indonesia, Semarang",
                "indonesia, Batu",
                "indonesia, Blitar",
                "indonesia, Kediri",
                "indonesia, Mojokerto",
                "indonesia, Malang",
                "indonesia, Madiun",
                "indonesia, Surabaya",
                "indonesia, Probolinggo",
                "indonesia, Pasuruan",
                "indonesia, Yogyakarta",
                "indonesia, Cilegon",
                "indonesia, Serang",
                "indonesia, Tanggerang",
                "indonesia, Tanggerang Selatan",
                "indonesia, Pangkal Pinang",
                "indonesia, Bengkulu",
                "indonesia, Pontianak",
                "indonesia, Singkawang",
                "indonesia, Banjarbaru",
                "indonesia, Banjarmasin",
                "indonesia, Palangkaraya",
                "indonesia, Balik Papan",
                "indonesia, Bontang",
                "indonesia, Samarinda",
                "indonesia, Tarakan",
                "indonesia, Batam",
                "indonesia, Tanjung Pinang",
                "indonesia, Bandar Lampung",
                "indonesia, Metro",
                "indonesia, Ternate",
                "indonesia, Tidore Kepulauan",
                "indonesia, Ambon",
                "indonesia, Tual",
                "indonesia, Bima",
                "indonesia, Mataram",
                "indonesia, Kupang",
                "indonesia, Denpasar",
                "indonesia, Makasar",
                "indonesia, Palopo",
                "indonesia, Parepare",
                "indonesia, Palu",
                "indonesia, Baubau",
                "indonesia, Kandari",
                "indonesia, Bitung",
                "indonesia, Kotamobagu",
                "indonesia, Manado",
                "indonesia, Tomohon",
                "indonesia, Gorontalo",
                "indonesia, Sorong",
                "indonesia, Jayapura",
                "indonesia, Dumai",
                "indonesia, Pekanbaru",
                "iran, Tehran",
        "iraq, Baghdad",
        "ireland, Dublin",
        "isle of man, Douglas",
        "israel, Jerusalem",
        "italy, Rome",
        "jamaica, Kingston",
        "japan, Tokyo",
        "jersey, Saint Helier",
        "jordan, Amman",
        "kazakhstan, Astana",
        "kenya, Nairobi",
        "kiribati, Tarawa",
        "kuwait, Kuwait City",
        "kyrgyzstan, Bishkek",
        "laos, Vientiane",
        "latvia, Riga",
        "lebanon, Beirut",
        "lesotho, Maseru",
        "liberia, Monrovia",
        "libya, Tripoli",
        "liechtenstein, Vaduz",
        "lithuania, Vilnius",
        "luxembourg, Luxembourg",
        "macau, N/A",
        "macedonia, Skopje",
        "madagascar, Antananarivo",
        "malawi, Lilongwe",
        "malaysia, Kuala Lumpur",
        "maledives, Male",
        "mali, Bamako",
        "malta, Valletta",
        "marshall islands, Majuro",
        "martinique, Fort-de-France",
        "mauretania, Nouakchott",
        "mauritius, Port Louis",
        "mexico, Mexico City",
        "micronesia, Palikir",
        "moldova, Chisinau",
        "monaco, Monaco",
        "mongolia, Ulaanbaatar",
        "montserrat, Plymouth",
        "morocco, Rabat",
        "mozambique, Maputo",
        "namibia, Windhoek",
        "nauru, Yaren",
        "nepal, Kathmandu",
        "netherlands, Amsterdam",
        "netherlands antilles, Willemstad",
        "new zealand, Wellington",
        "nicaragua, Managua",
        "niger, Niamey",
        "nigeria, Abuja",
        "niue, Alofi",
        "norfolk island, Kingston",
        "northern mariana islands, Saipan",
        "north korea, Pyongyang",
        "norway, Oslo",
        "oman, Muscat",
        "pakistan, Islamabad",
        "palau, Melekeok",
        "panama, Panama City",
        "papua new guinea, Port Moresby",
        "paraquay, Asuncion",
        "peru, Lima",
        "philippines, Manila",
        "pitcairn islands, Adamstown",
        "poland, Warsaw",
        "portugal, Lisbon",
        "puerto rico, San Juan",
        "qatar, Doha",
        "romania, Bucharest",
        "russia, Moscow",
        "rwanda, Kigali",
        "saint helena, Jamestown",
        "saint kitts and nevis, Basseterre",
        "saint lucia, Castries",
        "saint pierre and miquelon, Saint-Pierre",
        "saint vincent and the grenadines, Kingstown",
        "samoa, Apia",
        "san marino, San Marino",
        "sao tome and principe, Sao Tome",
        "saudi arabia, Riyadh",
        "scotland, Edinburgh",
        "senegal, Dakar",
        "serbia montenegro, Belgrade",
        "seychelles, Victoria",
        "sierra leone, Freetown",
        "singapore, Singapore",
        "slovakia, Bratislava",
        "slovenia, Ljubljana",
        "solomon islands, Honiara",
        "somalia, Mogadishu",
        "south africa, Pretoria",
        "south georgia, King Edward Point",
        "south korea, Seoul",
        "spain, Madrid",
        "sri lanka, Colombo",
        "sudan, Khartoum",
        "suriname, Paramaribo",
        "swaziland, Mbabane",
        "sweden, Stockholm",
        "switzerland, Bern",
        "syria, Damascus",
        "taiwan, Taipei",
        "tajikistan, Dushanbe",
        "tanzania, Dar es Salaam",
        "thailand, Bangkok",
        "tibet, Lhasa",
        "togo, Lome",
        "tonga, Nuku'alofa",
        "trinidad and tobago, Port of Spain",
        "tunisia, Tunis",
        "turkey, Ankara",
        "turkmenistan, Ashgabat",
        "turks and caicos islands, Grand Turk",
        "tuvalu, Funafuti",
        "uganda, Kampala",
        "ukraine, Kyiv",
        "united arab emirates, Abu Dhabi",
        "uruquay, Montevideo",
        "usa, Washington",
        "uzbekistan, Tashkent",
        "vanuatu, Port-Vila",
        "vatican city, Vatican City",
        "venezuela, Caracas",
        "vietnam, Hanoi",
        "virgin islands, Charlotte Amalie",
        "wales, Cardiff",
        "wallis and futuna, Mata-Utu",
        "yemen, Sanaa",
        "zambia, Lusaka",
        "zimbabwe, Harare"};

        list = findViewById(R.id.listview);
        searchView = findViewById(R.id.sv_search);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryNameList);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String [] temp = arrayAdapter.getItem(i).split("[,]", 0);
                String chosenCountry = temp[1];

                Intent intent = new Intent(list.getContext(),
                        MainActivity.class);
                intent.putExtra("chosenCountry", chosenCountry);
                list.getContext().startActivity(intent);
            }
        });

//        searchView.setQueryHint("Ketik untuk mencari...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //kalo ngetik di searchview trus enter
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            //kalo ngetik di searchview
            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}