var map = L.map('map').setView([47.15, 27.58], 7);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    noWrap: true,     
    bounds: [
        [-90, -180],
        [90, 180]
      ],
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'  
}).addTo(map);

//Location of souvenir shops
var locations = [
    //Romania
    { name: 'Mariuca Artizanat', lat: 47.1562124209384, lng: 27.58920863887209 },
    { name: 'La Șezătoare', lat: 47.15547300234092, lng: 27.60592846461859 },
    { name: 'Bobo Shop', lat: 47.15603268247656, lng: 27.589139350114813 },
    { name: 'Old Town Souvenirs', lat:44.432249874266084 , lng:26.100000885966196 },
    { name: 'Romanian Boutique', lat:44.431036529328374 , lng:26.10400613119477 },
    { name: 'Magazin de Suveniruri Grădina Botanică', lat:44.43693418963775 , lng:26.061096312466187 },
    { name: 'Divertis Shop', lat:46.77142973027822 , lng:23.626931626304764 },
    { name: 'Art Deco Gift & Souvenirs', lat:46.771859259739045 , lng:23.587555126191482 },
    { name: 'Indra Suvenir', lat:45.751267393883126 , lng:21.22512362199101 },
    { name: 'Evix Gift Shop', lat:45.75677384962648 , lng:21.229217149625722 },
    { name: 'Souvenir Shop', lat:47.054603316383904, lng:21.930189263500644  },
    { name: 'KingArt', lat:47.05836595690061 , lng:21.932647267613593 },
    { name: 'Casa Mereuta', lat:47.66511055684137 , lng:26.266957417126157 },
    { name: 'ART CRAFT SOUVENIRES', lat:44.178931114571796 , lng:28.647307749627444 },
    { name: 'Handmade and vintage', lat:44.9449832730316 , lng: 26.02639923120766 },
    { name: 'INSPIRATIO', lat:45.64103754517759 , lng:25.58744244284123 },
    { name: 'RetroChique', lat:45.79475877824685 , lng:24.150718480228807 },
    { name: 'SOUVENIRS - CADOURI', lat:47.66055743438093 , lng:23.5808751650289 },
    { name: 'Charisma Mercur', lat:44.31745575852909 , lng:23.793495199277086 },
    { name: 'Corund', lat:46.48324133847216, lng:25.180664597577675  },
    { name: 'Gift & Memories', lat:45.45474304305913, lng:28.031337149781848 },

    //Europe
    { name: 'Souvenirs Folk & Art', lat:47.025458463863494, lng:28.83595684079878  },
    { name: 'Svit Suveniriv', lat:50.445573456452266 , lng:30.54771315828244 },
    { name: 'Ukrainian souvenir', lat:49.23280293472037 , lng:28.472704214629523 },
    { name: 'Top Gifts Ltd', lat:42.75877111838939, lng:23.299657625635735  },
    { name: 'Bulgarian Souvenirs & Gifts', lat:42.15111518738289 , lng:24.748952911251003 },
    { name: 'Serbian souvenirs', lat:43.325326870407686, lng:21.89498055643063 },
    { name: 'Greco Souvenir', lat:37.97232568948804, lng:23.731901862186504  },
    { name: 'Creta Souvenirs by Belvedere Village', lat:35.406202538755636 , lng:25.01703536823731 },
    { name: 'Y.M. STOA Souvenir Shop', lat:34.77883432012761, lng:32.41927957095483  },
    { name: 'Turkuaz Bote', lat:41.0092443361225 , lng:28.96869417189409 },
    { name: 'Ankara Tesbih', lat:39.9430517293956 , lng:32.857471855989054 },
    { name: 'Souvenir shop Montenegro', lat:42.42694778243942 , lng: 18.76854561472441 },
    { name: 'Smile Souvenir', lat:47.49656773862449, lng: 19.060102281503582 },
    { name: 'THE WORLD TO GO VIENNA', lat:48.20634480369803, lng:16.367750338363738  },
    { name: 'Souvenirs Prague shop', lat:50.08798874456825, lng:14.42657642309324  },
    { name: 'Souvenirs & more ZAGREB', lat:45.8142761356609, lng:15.975814645778454 },
    { name: 'Treasures of Slovenia Bled', lat:46.36770808028881 , lng: 14.109345943340708 },
    { name: 'Make in the art of souvenir', lat:41.89946291041884 , lng:12.477154141819677 },
    { name: 'Milan Leo Souvenirs', lat:45.47146214245814, lng:9.178344216050853  },
    { name: 'Souvenir Aurora', lat:40.850872931326144, lng:14.256776905592984  },
    { name: 'Souvenir Gadget Torino Kiosko', lat:45.06834461520061, lng:7.690100204407933  },
    { name: 'Par`Ici', lat:48.84335333119563 , lng:2.3494606092248618 },
    { name: 'Marseille In The Box', lat:43.29635932881736, lng: 5.374717053941206  },
    { name: 'Souvenirs Flohr', lat:48.13752001794123, lng:11.57960241173347  },
    { name: 'Stadtmitte - Central Berlin Souvenirs', lat:52.51473315073094 , lng:13.39332962019113 },
    { name: 'B de Barcelona Souvenirs', lat:41.40663866757673 , lng:2.174569272932851 },
    { name: 'Madrid Souvenirs', lat:40.41801393343497 , lng:-3.7027519180976363 },
    { name: 'Souvenir de Lisboa', lat:38.71542954530249 , lng:-9.139743162677908 },
    { name: 'Love London Souvenirs', lat:51.52207120174617, lng:-0.12460118207956762  },
    { name: 'Manchester Souvenirs Gifts', lat:53.480985587972434 , lng:-2.247711236150425 },
    { name: 'The Temple Bar Trading Company', lat:53.34553648580675 , lng:-6.263902971859478 },
    { name: 'Amsterdam Today Souvenirs gifts', lat:52.3738597966593 , lng:4.893808553161458 },
    { name: 'CADEAUX ET SOUVENIRS DE BRUXELLES', lat:50.84695692815239 , lng: 4.3550929210224405 },
    { name: 'Souvenir Plaza', lat:52.24908786267512, lng:21.012301956863837  },
    { name: 'Alle Tiders', lat:59.913072871972496, lng:10.73363805938578  },
    { name: 'The Old Town Souvenir', lat:59.32587509656614, lng:18.068874876736498  },
    { name: 'Flying Tiger Copenhagen', lat:59.334649927111855 , lng:18.032317210232407 },
    { name: 'Annensoppi', lat:60.172342457609325 , lng:24.925571080865552 },
    { name: 'Skazka. Russian souvenirs', lat:55.74766809856332, lng:37.58624520081586  },
    { name: 'Souvenir', lat:59.9311875435712 , lng:30.35908102314536 },
    
    //North America
    { name: 'Canada In A Basket', lat:45.427446807137144 , lng:-75.69236841608082 },
    { name: 'Souvenir du Québec', lat:45.50976901020902, lng:-73.55263459946244  },
    { name: 'Memories of New York', lat:40.74321133178105 , lng:-73.98878988767156 },
    { name: 'Alex`s Flamingo Groves & Gift Shop', lat:26.057318341332966, lng:-80.14358257977014  },
    { name: 'Bye Bye Chicago', lat:41.88744595209517 , lng:-87.6247735941656 },
    { name: 'Hearts Delight', lat:36.82474807641781 , lng:-119.70184379147413 },
    { name: 'Texas Souvenirs', lat:33.002448122332574 , lng:-96.97092173929953 },
    { name: 'Dantoale Crafts', lat:19.42493587900673 , lng:-99.16533914225325 },
    { name: 'Simply Seattle', lat:47.61000448203862, lng:-122.34082999526488  },

    //South America
    { name: 'Souvenir Palace', lat:-22.966654231305863, lng: -43.1798005332022  },
    { name: 'Paulista Souvenir', lat:-23.558141818187238, lng:-46.659163984092764  },
    { name: 'I Love Gifts', lat:-34.60433699071727 , lng:-58.3822959832561 },
    { name: 'Artesanías El Jaguar', lat:4.597604002330658, lng: -74.06935158871762  },
    { name: 'Recuerda Chile', lat:-33.437271729857166 , lng:-70.6422248260007 },
   
    //Asia
    { name: 'Souvenir from Tokyo', lat:35.66487014665061 , lng:139.72644154801088 },
    { name: 'Grand Kiosk Kyoto', lat:34.98469946548183 , lng:135.76008572784332 },
    { name: 'Arirang Souvenir Shop', lat:37.56869785781383 , lng: 127.00857667426673 },
    { name: 'Great Wall Souvenirs', lat:40.35619083791019 , lng:116.0149890097126 },
    { name: 'Shanghai Souvenirs Shopping Center', lat:31.235875845604596 , lng: 121.47973604106707 },
    { name: 'Souvenirs Bangkok', lat:13.734620031331831 , lng:100.56353715976752 },
    { name: '79 Souvenir Shop', lat:21.03651064538376, lng:105.84719892022379  },
    { name: 'Vladgifts', lat:43.11445478756548, lng:131.8860812972633  },
    { name: 'Souvenir Island General Merchandise', lat:14.597786516905355 , lng:120.98247469345527 },
    { name: 'Bugis Souvenir Shop', lat:1.3005143539184663 , lng:103.85512949478675 },
    { name: 'Indonesian Souvenir', lat:-6.2448674906129655 , lng:106.80053345759059 },
    { name: 'Souvenir shop', lat:28.613942261698522, lng:77.23322548080274  },
    { name: 'The Museum Shop', lat:18.926718473102106, lng:72.8315657617097  },
    { name: 'City Sun Gifts', lat:23.58360510931031, lng:58.42501427248122  },
    { name: 'I Love Dubai', lat:25.19907380501486 , lng:55.27728092210133 },
    { name: 'Old City Gift Shop - Souvenirs', lat:31.775500325595925, lng:35.23210649820687  },
    { name: 'Rafis Souvenirs and Dead Sea Products', lat:32.073087523721846 , lng:34.76770904961483 },

    //Africa
    { name: 'Gift Shop Bazaar', lat:30.04734640365315, lng: 31.234856757955658  },
    { name: 'Souvenir du Maroc', lat:33.598383825386755 , lng:-7.618605294549723 },
    { name: 'AWESOME AFRICA', lat:-29.72520100297825, lng: 31.087044818562234  },
    { name: 'De Aubry Souvenirs', lat:6.638698124714292 , lng: 3.3750027304424743 },
    { name: 'Anne Calfo', lat:14.72860090471664, lng:-17.49705269961982  },
    { name: 'Gift Corner', lat:-4.300844427015244 , lng:15.310944278587614 },
    { name: 'All In One Souvenir & Gift Shop', lat:9.02156114720214 , lng:38.753330352283015 },

    //Australia and Oceania
    { name: 'The Museum Shop', lat:-35.29251036339746, lng:149.12063595379598  },
    { name: 'Downtown Souvenirs & Gifts', lat:-33.87129705628735 , lng:151.2071286509986 },
    { name: 'Bits of Australia', lat:-33.85725860253294 , lng:151.19342629684954 },
    { name: 'Simply New Zealand - Wanaka', lat:-44.695336476949926 , lng:169.13493503271903 },
    { name: 'Boroko Handicrafts Market', lat:-9.466021227975634 , lng:147.19433573777894 },

    
];

//Set icon and anchor image/sizes
var shopIcon = L.icon({
    iconUrl: "Images/shop.jpg",
    iconSize: [30, 40],
    iconAnchor: [15, 0],
});

//show all the locations
locations.forEach(function (location) {
    var marker = L.marker([location.lat, location.lng], { icon: shopIcon }).addTo(map);
    marker.bindPopup(location.name);
});

