package it.unisa.is.c09.digitaldonation.web.autogenerate;

import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class RandChoiser {

  /**
   * Metodo che fa parsing dalla (Date) date alla Stringa gg-mm-aaaa.
   *
   * @param date data in input
   * @return stringa gg-mm-aaaa
   */
  public static String parsDateToString(Date date) {
    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    String date1 = format1.format(date);
    return date1;
  }

  /**
   * Metodo che fa parsing dalla stringa date gg-mm-aaaa all'oggetto Date.
   *
   * @param date stringa di formato gg-mm-aaa
   * @return oggetto Date
   * @throws ParseException
   */
  public static Date parsStringToDate(String date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date newDate = formatter.parse(date);
    return newDate;
  }

  /**
   * Metodo che crea una data futura rispetto alla data passata in input.
   *
   * @param date data passata in input
   * @return data > date come parametro
   */
  public static Date generateFutureDate(Date date) {
    Date newDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(randBetween(20, 60)));
    return newDate;
  }

  /**
   * Metodo che genra una sigla di provincia a caso.
   *
   * @return una sigla di provincia a caso
   */
  public static String generateProvincia() {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rand = new Random();
    StringBuilder builder;
    do {
      builder = new StringBuilder();
      for (int i = 0; i < 2; i++) {
        builder.append(letters.charAt(rand.nextInt(letters.length())));
      }
    } while (!(builder.toString().matches(Seduta.PROVINCIA_REGEX)));
    return builder.toString();
  }

  /**
   * Metodo che genra un cap casuale.
   *
   * @return un cap casuale
   */
  public static String generateCap() {
    String cap;
    do {
      cap = String.valueOf(randBetween(10000, 99999));
      System.out.println(cap);
    } while (!(cap.matches(Seduta.CAP_REGEX)));
    return cap;
  }

  /**
   * Metodo che ritorna una Citta casuale.
   *
   * @return genera una citta casuale
   */
  public static String generateCitta() {
    Random random = new Random();
    String citta;
    String[] name =
            {"Abano Terme", "Abbadia Lariana", "Abbasanta", "Abbiategrasso", "Abriola", "Accadia", "Accettura", "Accumoli", "Acerno", "Aci Bonaccorsi", "Aci Catena", "Acireale", "Acquafondata", "Acquafredda", "Acquanegra Cremonese", "Acquapendente", "Acquarica del Capo", "Acquasanta Terme", "Acquaviva Collecroce", "Acquaviva d'Isernia", "Acquaviva Platani", "Acqui Terme", "Acuto", "Adrano", "Adrara San Rocco", "Adro", "Affile", "Africo", "Agerola", "Agira", "Agliano Terme", "Aglientu", "Agnadello", "Agnone", "Agordo", "Agra", "Agrate Conturbia", "Agropoli", "Agugliaro", "Aidomaggiore", "Aielli", "Aiello del Friuli", "Aieta", "Ailoche", "Airola", "Airuno", "Ala", "Ala di Stura", "Alagna Valsesia", "Alano di Piave", "Alatri", "Alba Adriatica", "Albairate", "Albano di Lucania", "Albano Sant'Alessandro", "Albaredo Arnaboldi", "Albaredo per San Marco", "Albaretto della Torre", "Albenga", "Alberobello", "Albese con Cassano", "Albi", "Albiano d'Ivrea", "Albidona", "Albinea", "Albiolo", "Albissola Marina", "Albonese", "Albugnano", "Alcamo", "Aldeno", "Ales", "Alessandria del Carretto", "Alessano", "Alfano", "Alfianello", "Alfonsine", "Algua", "Alì Terme", "Aliano", "Alice Castello", "Alife", "Aliminusa", "Alleghe", "Allerona", "Allumiere", "Almè", "Almenno San Salvatore", "Alonte", "Alpignano", "Alserio", "Altare", "Altavilla Milicia", "Altavilla Silentina", "Altidona", "Altino", "Altivole", "Altofonte", "Altopascio", "Alvignano", "Alzano Lombardo", "Alzate Brianza", "Amandola", "Amaro", "Amaseno", "Amatrice", "Amblar", "Amelia", "Ameno", "Ampezzo", "Anagni", "Ancona", "Andalo", "Andezeno", "Andorno Micca", "Andrate", "Andretta", "Andriano", "Anfo", "Anghiari", "Angolo Terme", "Angrogna", "Anguillara Veneta", "Annone di Brianza", "Anoia", "Anterivo", "Anticoli Corrado", "Antillo", "Antrodoco", "Anversa degli Abruzzi", "Anzano di Puglia", "Anzio", "Anzola d'Ossola", "Apecchio", "Apiro", "Appiano Gentile", "Appignano", "Aprica", "Apricena", "Aprilia", "Aquila d'Arroscia", "Aquilonia", "Aradeo", "Aramengo", "Arborea", "Arbus", "Arce", "Arcevia", "Arcidosso", "Arcisate", "Arcola", "Arconate", "Arcugnano", "Ardauli", "Ardenno", "Ardore", "Arena Po", "Arese", "Argegno", "Argenta", "Arguello", "Ari", "Ariano nel Polesine", "Arielli", "Arignano", "Arizzano", "Arluno", "Armento", "Armungia", "Arnara", "Arnesano", "Arona", "Arpaia", "Arpino", "Arquà Polesine", "Arquata Scrivia", "Arrone", "Arsiè", "Arsita", "Arta Terme", "Artena", "Arvier", "Arzago d'Adda", "Arzano", "Arzergrande", "Ascea", "Ascoli Piceno", "Ascrea", "Asigliano Veneto", "Asola", "Assago", "Assisi", "Assolo", "Asti", "Ateleta", "Atena Lucana", "Atina", "Atri", "Attigliano", "Atzara", "Augusta", "Aulla", "Aurigo", "Ausonia", "Avegno", "Avella", "Averara", "Avetrana", "Aviano", "Avigliana", "Avigliano Umbro", "Avise", "Avolasca", "Aymavilles", "Azzanello", "Azzano Decimo", "Azzano San Paolo", "Azzio", "Baceno", "Badalucco", "Badia", "Badia Pavese", "Badia Tedalda", "Bagaladi", "Bagnacavallo", "Bagnara di Romagna", "Bagnaria Arsa", "Bagnatica", "Bagno a Ripoli", "Bagnoli del Trigno", "Bagnoli Irpino", "Bagnolo del Salento", "Bagnolo in Piano", "Bagnolo Piemonte", "Bagnone", "Bagolino", "Baiano", "Baiso", "Balangero", "Baldissero Canavese", "Baldissero Torinese", "Balestrino", "Ballao", "Balmuccia", "Balsorano", "Balzola", "Banchette", "Banzi", "Baradili", "Baranello", "Baranzate", "Baratili San Pietro", "Barbara", "Barbarano Vicentino", "Barbariga", "Barberino di Mugello", "Barbianello", "Barbona", "Barchi", "Bard", "Bardi", "Bardolino", "Bareggio", "Baressa", "Barga", "Barge", "Bari", "Bariano", "Barile", "Barlassina", "Barni", "Barone Canavese", "Barrafranca", "Barrea", "Barzago", "Barzanò", "Basaluzzo", "Baschi", "Baselga di Pinè", "Basiano", "Basiglio", "Bassano Bresciano", "Bassano in Teverina", "Bassiano", "Bastia Mondovì", "Bastida de' Dossi", "Bastiglia", "Battifollo", "Battuda", "Bauladu", "Baveno", "Bedero Valcuvia", "Bedollo", "Bedulita", "Beinasco", "Belcastro", "Belforte all'Isauro", "Belforte Monferrato", "Belgirate", "Bellagio", "Bellante", "Bellegra", "Bellinzago Lombardo", "Bellizzi", "Bellosguardo", "Bellusco", "Belmonte Castello", "Belmonte in Sabina", "Belmonte Piceno", "Belsito", "Belvedere Langhe", "Belvedere Ostrense", "Belvì", "Bene Lario", "Benestare", "Benevello", "Benna", "Berbenno", "Berceto", "Beregazzo con Figliaro", "Bergamasco", "Bergantino", "Bergolo", "Bernalda", "Bernate Ticino", "Berra", "Bertinoro", "Bertonico", "Berzano di Tortona", "Berzo Inferiore", "Besana in Brianza", "Besate", "Besenzone", "Besozzo", "Bettola", "Beura-Cardezza", "Beverino", "Biancavilla", "Bianco", "Biandronno", "Bianzè", "Biassono", "Bibbiena", "Bibiana", "Bicinicco", "Biella", "Bieno", "Bigarello", "Binasco", "Bioglio", "Bione", "Bisaccia", "Bisceglie", "Bisenti", "Bistagno", "Bitetto", "Bitritto", "Bivona", "Bizzarone", "Blello", "Blessagno", "Blufi", "Bobbio", "Boca", "Boccioleto", "Bodio Lomnago", "Boffalora Sopra Ticino", "Bognanco", "Boissano", "Bolano", "Bolgare", "Bollengo", "Bolognano", "Bolognola", "Bolsena", "Bolzano", "Bolzano Vicentino", "Bomba", "Bompietro", "Bonarcado", "Bonate Sopra", "Bonavigo", "Bondo", "Bonea", "Bonemerse", "Bonito", "Bono", "Bonvicino", "Borca di Cadore", "Bordighera", "Bore", "Borgarello", "Borgetto", "Borghetto di Borbera", "Borghetto Lodigiano", "Borghi", "Borgiallo", "Borgo a Mozzano", "Borgo di Terzo", "Borgo Priolo", "Borgo San Giacomo", "Borgo San Lorenzo", "Borgo San Siro", "Borgo Tossignano", "Borgo Valsugana", "Borgo Vercelli", "Borgofranco d'Ivrea", "Borgolavezzaro", "Borgomanero", "Borgomasino", "Borgonovo Val Tidone", "Borgoratto Mormorolo", "Borgorose", "Borgosesia", "Bormio", "Borno", "Borore", "Borriana", "Bortigali", "Borutta", "Bosa", "Boschi Sant'Anna", "Bosco Marengo", "Boscoreale", "Bosentino", "Bosio", "Bosnasco", "Bossolasco", "Botrugno", "Botticino", "Bova", "Bovalino", "Boves", "Boville Ernica", "Bovisio-Masciago", "Bovolone", "Bozzolo", "Bracca", "Bracigliano", "Brallo di Pregola", "Brandico", "Branzi", "Brebbia", "Bregano", "Bregnano", "Breia", "Brembate di Sopra", "Brembio", "Brendola", "Brennero", "Brenta", "Brentonico", "Brescello", "Bresimo", "Bressanone", "Bresso", "Brezzo di Bedero", "Briatico", "Brienno", "Briga Alta", "Brignano Gera d'Adda", "Brindisi", "Brinzio", "Brione", "Briosco", "Brissago-Valtravaglia", "Brittoli", "Broccostella", "Brognaturo", "Brondello", "Bronte", "Brossasco", "Brovello-Carpugnino", "Brugherio", "Brugnato", "Bruino", "Brunate", "Brunico", "Brusaporto", "Brusciano", "Brusnengo", "Bruzolo", "Bubbiano", "Buccheri", "Bucciano", "Buccino", "Buddusò", "Budoni", "Buggerru", "Buglio in Monte", "Buguggiate", "Bulciago", "Bultei", "Buonabitacolo", "Buonconvento", "Burago di Molgora", "Burgio", "Buriasco", "Buronzo", "Busalla", "Busano", "Buscate", "Buseto Palizzolo", "Bussero", "Bussi sul Tirino", "Bussolengo", "Busto Arsizio", "Butera", "Buttapietra", "Buttigliera d'Asti", "Ca' d'Andrea", "Cabiate", "Caccamo", "Cadegliano-Viconago", "Cadeo", "Cadoneghe", "Cadrezzate", "Cafasse", "Cagli", "Caglio", "Cagnano Varano", "Cagnò", "Caiazzo", "Caino", "Cairano", "Cairo Montenotte", "Calabritto", "Calamandrana", "Calangianus", "Calasca-Castiglione", "Calascio", "Calatabiano", "Calavino", "Calceranica al Lago", "Calciano", "Calcinate", "Calcio", "Caldaro sulla Strada del Vino", "Calderara di Reno", "Caldiero", "Caldonazzo", "Calenzano", "Calice al Cornoviglio", "Calimera", "Calizzano", "Calliano", "Calolziocorte", "Calosso", "Caltabellotta", "Caltanissetta", "Caltignaga", "Caltrano", "Caluso", "Calvanico", "Calvello", "Calvenzano", "Calvi", "Calvi Risorta", "Calvignasco", "Calvizzano", "Camaiore", "Camandona", "Cambiago", "Cambiasca", "Camerana", "Camerano Casasco", "Camerata Nuova", "Cameri", "Camerota", "Caminata", "Camino", "Camisano", "Cammarata", "Camogli", "Campagna Lupia", "Campagnatico", "Campagnola Emilia", "Camparada", "Campello sul Clitunno", "Campi Bisenzio", "Campiglia Cervo", "Campiglia Marittima", "Campione d'Italia", "Campli", "Campo di Giove", "Campo Ligure", "Campo San Martino", "Campobasso", "Campobello di Mazara", "Campodarsego", "Campodimele", "Campodolcino", "Campofelice di Fitalia", "Campofilone", "Campoformido", "Campogalliano", "Campoli Appennino", "Campolieto", "Campolongo sul Brenta", "Campomaggiore", "Campomorone", "Campora", "Camporgiano", "Camporotondo di Fiastrone", "Camposampiero", "Camposanto", "Campotosto", "Canal San Bovo", "Canale d'Agordo", "Canaro", "Cancellara", "Canda", "Candelo", "Candia Lomellina", "Candida", "Candiolo", "Canelli", "Caneva", "Canicattì", "Canino", "Canistro", "Cannalonga", "Cannero Riviera", "Canneto sull'Oglio", "Cannole", "Canonica d'Adda", "Canosa Sannita", "Canossa", "Cantagallo", "Cantalupa", "Cantalupo Ligure", "Cantarana", "Canterano", "Cantoira", "Canzano", "Caorle", "Capaccio", "Capalbio", "Capannori", "Capergnanica", "Capiago Intimiano", "Capistrello", "Capizzi", "Capo di Ponte", "Capodimonte", "Capoliveri", "Caponago", "Caposele", "Capovalle", "Cappella Cantone", "Cappella Maggiore", "Capracotta", "Capraia Isola", "Capranica", "Caprarica di Lecce", "Caprauna", "Caprezzo", "Capri Leone", "Capriano del Colle", "Capriate San Gervasio", "Caprie", "Capriglio", "Caprino Bergamasco", "Capriolo", "Capua", "Caraffa del Bianco", "Caraglio", "Caramanico Terme", "Carapelle", "Carasco", "Carate Brianza", "Caravaggio", "Caravino", "Carbognano", "Carbonara di Nola", "Carbonara Scrivia", "Carbone", "Carbonia", "Carceri", "Cardano al Campo", "Cardedu", "Cardinale", "Careggine", "Carenno", "Careri", "Caresanablot", "Carfizzi", "Cariati", "Carignano", "Carinaro", "Carinola", "Carisolo", "Carlazzo", "Carlino", "Carlopoli", "Carmiano", "Carmignano di Brenta", "Carnate", "Carolei", "Caronia", "Caronno Varesino", "Carovigno", "Carpaneto Piacentino", "Carpasio", "Carpenedolo", "Carpi", "Carpignano Salentino", "Carpineti", "Carpineto Romano", "Carpino", "Carrara", "Carrega Ligure", "Carrodano", "Carrù", "Cartigliano", "Cartoceto", "Cartura", "Carugo", "Carvico", "Casabona", "Casacanditella", "Casal Cermelli", "Casal Velino", "Casalattico", "Casalbordino", "Casalborgone", "Casalbuttano ed Uniti", "Casalduni", "Casale Cremasco-Vidolasco", "Casale Litta", "Casale Monferrato", "Casalecchio di Reno", "Casaleggio Novara", "Casaletto Ceredano", "Casaletto Lodigiano", "Casaletto Vaprio", "Casalgrande", "Casalincontrada", "Casalmaggiore", "Casalmorano", "Casalnoceto", "Casalnuovo Monterotaro", "Casalpusterlengo", "Casalserugo", "Casalvecchio di Puglia", "Casalvieri", "Casalzuigno", "Casamassima", "Casandrino", "Casanova Lerrone", "Casape", "Casapinta", "Casapulla", "Casargo", "Casarsa della Delizia", "Casasco", "Casatenovo", "Casavatore", "Cascia", "Casciana Terme", "Cascinette d'Ivrea", "Caselette", "Caselle in Pittari", "Caselle Lurani", "Caserta", "Casignana", "Casirate d'Adda", "Casnate con Bernate", "Casola di Napoli", "Casola Valsenio", "Casole d'Elsa", "Casorate Primo", "Casorezzo", "Casorzo", "Caspoggio", "Cassago Brianza", "Cassano d'Adda", "Cassano Irpino", "Cassano Spinola", "Cassaro", "Cassina de' Pecchi", "Cassina Valsassina", "Cassine", "Cassinetta di Lugagnano", "Cassola", "Castagnaro", "Castagneto Po", "Castagnole delle Lanze", "Castagnole Piemonte", "Castano Primo", "Castegnato", "Castel Baronia", "Castel Bolognese", "Castel Castagna", "Castel Condino", "Castel d'Ario", "Castel del Giudice", "Castel del Piano", "Castel di Casio", "Castel di Iudica", "Castel di Lucio", "Castel di Sasso", "Castel Focognano", "Castel Gabbiano", "Castel Giorgio", "Castel Guelfo di Bologna", "Castel Maggiore", "Castel Morrone", "Castel Rocchero", "Castel San Giorgio", "Castel San Lorenzo", "Castel San Pietro Romano", "Castel San Vincenzo", "Castel Sant'Elia", "Castel Vittorio", "Castelbaldo", "Castelbellino", "Castelbianco", "Castelbuono", "Castelcovati", "Casteldaccia", "Casteldelfino", "Castelfidardo", "Castelfondo", "Castelfranci", "Castelfranco di Sotto", "Castelfranco in Miscano", "Castelgomberto", "Castelguglielmo", "Castellabate", "Castell'Alfero", "Castellammare del Golfo", "Castellamonte", "Castellana Sicula", "Castellania", "Castellar", "Castellarano", "Castell'Arquato", "Castell'Azzara", "Castellazzo Novarese", "Castelleone di Suasa", "Castelletto Cervo", "Castelletto di Branduzzo", "Castelletto Merli", "Castelletto Monferrato", "Castelletto Stura", "Castelli", "Castellina in Chianti", "Castellinaldo", "Castellino Tanaro", "Castello Cabiaglio", "Castello d'Argile", "Castello dell'Acqua", "Castello di Brianza", "Castello di Godego", "Castello Tesino", "Castellucchio", "Castelluccio Inferiore", "Castelluccio Valmaggiore", "Castelmagno", "Castelmassa", "Castelmezzano", "Castelnovetto", "Castelnovo del Friuli", "Castelnovo ne' Monti", "Castelnuovo Belbo", "Castelnuovo Bocca d'Adda", "Castelnuovo Bozzente", "Castelnuovo Cilento", "Castelnuovo della Daunia", "Castelnuovo di Conza", "Castelnuovo di Garfagnana", "Castelnuovo di Val di Cecina", "Castelnuovo Magra", "Castelnuovo Parano", "Castelnuovo Scrivia", "Castelpetroso", "Castelplanio", "Castelraimondo", "Castelsantangelo sul Nera", "Castelsardo", "Castelsilano", "Casteltermini", "Castelvecchio Calvisio", "Castelvecchio Subequo", "Castelverde", "Castelvetere in Val Fortore", "Castelvetrano", "Castelvetro Piacentino", "Castenaso", "Castiadas", "Castiglion Fiorentino", "Castiglione Chiavarese", "Castiglione d'Adda", "Castiglione del Genovesi", "Castiglione della Pescaia", "Castiglione di Garfagnana", "Castiglione d'Intelvi", "Castiglione Falletto", "Castiglione Messer Marino", "Castiglione Olona", "Castiglione Torinese", "Castilenti", "Castione Andevenno", "Castions di Strada", "Casto", "Castrezzato", "Castrignano de' Greci", "Castro", "Castro dei Volsci", "Castrocielo", "Castrolibero", "Castronovo di Sicilia", "Castropignano", "Castroregio", "Catania", "Catenanuova", "Cattolica", "Caulonia", "Cava de' Tirreni", "Cavacurta", "Cavaglietto", "Cavaglio-Spoccia", "Cavaion Veronese", "Cavallasca", "Cavallermaggiore", "Cavallino-Treporti", "Cavareno", "Cavaria con Premezzo", "Cavaso del Tomba", "Cavatore", "Cave", "Cavedine", "Cavenago di Brianza", "Cavezzo", "Cavour", "Cavriana", "Cazzago Brabbia", "Cazzano di Tramigna", "Ceccano", "Cecina", "Cedrasco", "Cefalù", "Ceglie Messapica", "Celenza sul Trigno", "Celico", "Cella Monte", "Cellara", "Cellatica", "Celle di Macra", "Celle Enomondo", "Celleno", "Cellino Attanasio", "Cellio", "Cembra", "Cenate Sopra", "Cencenighe Agordino", "Ceneselli", "Centa San Nicolò", "Cento", "Centrache", "Cepagatti", "Ceppo Morelli", "Cerami", "Cerano", "Ceranova", "Cercemaggiore", "Cercepiccola", "Cerchio", "Cercivento", "Cerda", "Ceregnano", "Ceres", "Cereseto", "Ceresole Reale", "Ceretto Lomellina", "Ceriale", "Ceriano Laghetto", "Cerignola", "Cermenate", "Cermignano", "Cernusco Lombardone", "Cerreto Castello", "Cerreto d'Esi", "Cerreto Grue", "Cerreto Laziale", "Cerretto Langhe", "Cerrione", "Cerro al Volturno", "Cerro Tanaro", "Cersosimo", "Certosa di Pavia", "Cervara di Roma", "Cervaro", "Cervatto", "Cervere", "Cerveteri", "Cervicati", "Cervignano del Friuli", "Cervino", "Cerzeto", "Cesana Brianza", "Cesano Boscone", "Cesara", "Cesate", "Cesenatico", "Cesio", "Cessalto", "Cessapalombo", "Cetara", "Cetona", "Ceva", "Challand-Saint-Anselme", "Chambave", "Champdepraz", "Charvensod", "Cherasco", "Chialamberto", "Chianche", "Chianni", "Chiaramonte Gulfi", "Chiarano", "Chiaravalle Centrale", "Chiaromonte", "Chiavari", "Chiaverano", "Chieri", "Chiesa in Valmalenco", "Chiesina Uzzanese", "Chieuti", "Chignolo d'Isola", "Chioggia", "Chions", "Chitignano", "Chiuppano", "Chiusa", "Chiusa di San Michele", "Chiusaforte", "Chiusano d'Asti", "Chiusavecchia", "Chiusi", "Chivasso", "Cianciana", "Cicagna", "Cicciano", "Ciciliano", "Ciconio", "Cigliè", "Cigole", "Cimadolmo", "Cimego", "Ciminna", "Cimolais", "Cinaglio", "Cingia de' Botti", "Cinigiano", "Cinisi", "Cinquefrondi", "Cinte Tesino", "Cinto Euganeo", "Ciorlano", "Circello", "Cirigliano", "Cirò", "Cis", "Cisano sul Neva", "Cislago", "Cismon del Grappa", "Cissone", "Cisterna di Latina", "Citerna", "Città di Castello", "Cittadella", "Cittanova", "Cittiglio", "Civenna", "Civezzano", "Cividale del Friuli", "Cividate Camuno", "Civita Castellana", "Civitacampomarano", "Civitanova del Sannio", "Civitaquana", "Civitella Alfedena", "Civitella d'Agliano", "Civitella di Romagna", "Civitella Messer Raimondo", "Civitella Roveto", "Civo", "Claut", "Clavesana", "Cles", "Clivio", "Clusone", "Coazze", "Coccaglio", "Cocquio-Trevisago", "Codevigo", "Codigoro", "Codogno", "Codrongianos", "Cogliate", "Cogoleto", "Cogorno", "Colbordolo", "Colfelice", "Colico", "Collalto Sabino", "Collazzone", "Colle d'Anchise", "Colle di Val d'Elsa", "Colle Sannita", "Colle Umberto", "Collecchio", "Colledara", "Colledimezzo", "Collegiove", "Collelongo", "Collepasso", "Colleretto Castelnuovo", "Collesalvetti", "Colletorto", "Colli a Volturno", "Colli sul Velino", "Collinas", "Collobiano", "Colmurano", "Cologna Veneta", "Cologno al Serio", "Colognola ai Colli", "Colonnella", "Colorina", "Colosimi", "Colzate", "Comacchio", "Comano Terme", "Comeglians", "Comerio", "Comignago", "Comitini", "Commessaggio", "Como", "Comun Nuovo", "Cona", "Conca dei Marini", "Concamarise", "Concesio", "Concordia Sagittaria", "Concorezzo", "Condofuri", "Condrò", "Confienza", "Conflenti", "Conselice", "Contessa Entellina", "Contrada", "Controne", "Conversano", "Conzano", "Copiano", "Corana", "Corbara", "Corbola", "Corciano", "Cordignano", "Coredo", "Coreglia Ligure", "Corfinio", "Coriano", "Corigliano d'Otranto", "Corio", "Corleto Monforte", "Cormano", "Corna Imagna", "Cornale", "Cornate d'Adda", "Cornedo Vicentino", "Corneliano d'Alba", "Corno di Rosazzo", "Cornovecchio", "Correggio", "Correzzola", "Corridonia", "Corsano", "Corsione", "Cortale", "Cortanze", "Corte Brugnatella", "Corte de' Frati", "Corte Palasio", "Cortemilia", "Cortenova", "Corteolona", "Cortina d'Ampezzo", "Cortino", "Corvara", "Corvino San Quirico", "Coseano", "Cosio d'Arroscia", "Cosoleto", "Cossano Canavese", "Cosseria", "Cossogno", "Cossombrato", "Costa di Mezzate", "Costa Masnaga", "Costa Valle Imagna", "Costa Volpino", "Costacciaro", "Costarainera", "Costigliole d'Asti", "Cotignola", "Cottanello", "Covo", "Craco", "Cravagliana", "Craveggia", "Crecchio", "Credera Rubbiano", "Cremella", "Cremeno", "Cremolino", "Cremosano", "Crespadoro", "Crespellano", "Crespina", "Cressa", "Crevalcore", "Crispano", "Crissolo", "Crocetta del Montello", "Crognaleto", "Cropani", "Crosia", "Crotone", "Crova", "Crucoli", "Cuccaro Monferrato", "Cucciago", "Cuggiono", "Cuglieri", "Cumiana", "Cunardo", "Cunevo", "Cuorgnè", "Cupra Marittima", "Cura Carpignano", "Cureggio", "Curinga", "Curno", "Cursi", "Curtarolo", "Curti", "Cusano Milanino", "Cusino", "Custonaci", "Cutro", "Cuveglio", "Daiano", "Dalmine", "Danta di Cadore", "Darè", "Dasà", "Daverio", "Dazio", "Decimoputzu", "Dego", "Delebio", "Delianuova", "Dello", "Denice", "Dernice", "Deruta", "Desana", "Desio", "Diamante", "Diano Castello", "Diano Marina", "Dicomano", "Dimaro", "Dipignano", "Divignano", "Dobbiaco", "Dogliani", "Dogna", "Dolceacqua", "Dolegna del Collio", "Dolo", "Domanico", "Domegge di Cadore", "Domodossola", "Domusnovas", "Donato", "Donnas", "Dorgali", "Dormelletto", "Dorsino", "Dosolo", "Dosso del Liro", "Dovadola", "Dozza", "Drapia", "Drenchia", "Drezzo", "Dro", "Druento", "Dualchi", "Due Carrare", "Dugenta", "Dumenza", "Durazzano", "Dusino San Michele", "Edolo", "Elice", "Ello", "Elva", "Empoli", "Enego", "Enna", "Entratico", "Episcopia", "Erba", "Erbezzo", "Erchie", "Erice", "Erto e Casso", "Erve", "Escalaplano", "Esine", "Esperia", "Este", "Etroubles", "Exilles", "Fabbriche di Vallico", "Fabriano", "Fabrizia", "Faedis", "Faedo Valtellino", "Faeto", "Faggeto Lario", "Fagnano Alto", "Fagnano Olona", "Faicchio", "Falciano del Massico", "Falconara Marittima", "Faleria", "Falerone", "Falmenta", "Falvaterra", "Fanano", "Fano", "Fara Filiorum Petri", "Fara in Sabina", "Fara Olivana con Sola", "Fara Vicentino", "Farigliano", "Farini", "Farra d'Alpago", "Farra d'Isonzo", "Fascia", "Faule", "Favara", "Favignana", "Feisoglio", "Felino", "Felizzano", "Feltre", "Fenestrelle", "Ferentillo", "Ferla", "Fermo", "Feroleto Antico", "Ferrandina", "Ferrara di Monte Baldo", "Ferrera di Varese", "Ferrere", "Ferruzzano", "Fiano", "Fiastra", "Ficarazzi", "Ficarra", "Fidenza", "Fiera di Primiero", "Fiesco", "Fiesse", "Fiesso Umbertiano", "Figline Valdarno", "Filacciano", "Filago", "Filattiera", "Filetto", "Filighera", "Filogaso", "Finale Emilia", "Fino del Monte", "Fiorano al Serio", "Fiorano Modenese", "Fiorenzuola d'Arda", "Firenzuola", "Fisciano", "Fiumalbo", "Fiume Veneto", "Fiumefreddo Bruzio", "Fiumicello", "Fiuminata", "Flaibano", "Flero", "Floridia", "Flumeri", "Flussio", "Foggia", "Fogliano Redipuglia", "Foiano della Chiana", "Folgaria", "Foligno", "Follo", "Fombio", "Fondi", "Fonni", "Fontana Liri", "Fontanarosa", "Fontanella", "Fontanelle", "Fontanetto Po", "Fontanile", "Fonte", "Fontecchio", "Fontegreca", "Fontevivo", "Foppolo", "Force", "Forcola", "Forenza", "Forgaria nel Friuli", "Forio", "Forlì del Sannio", "Formazza", "Formia", "Formigara", "Formigliana", "Fornace", "Forni Avoltri", "Forni di Sotto", "Forno di Zoldo", "Fornovo San Giovanni", "Fortezza", "Forza d'Agrò", "Fosdinovo", "Fossacesia", "Fossalta di Portogruaro", "Fossano", "Fossato Serralta", "Fossombrone", "Frabosa Soprana", "Fraconalto", "Fragneto L'Abate", "Fraine", "Francavilla al Mare", "Francavilla Bisio", "Francavilla di Sicilia", "Francavilla in Sinni", "Francica", "Francolise", "Frascarolo", "Frascineto", "Frassinelle Polesine", "Frassineto Po", "Frassino", "Frasso Sabino", "Fratta Polesine", "Frattamaggiore", "Fratte Rosa", "Fregona", "Fresonara", "Frignano", "Frisa", "Front", "Frontone", "Frosolone", "Frugarolo", "Fucecchio", "Fumane", "Funes", "Furci Siculo", "Furore", "Fuscaldo", "Fusine", "Gabbioneta-Binanuova", "Gabicce Mare", "Gadesco-Pieve Delmona", "Gaeta", "Gaggiano", "Gaglianico", "Gagliano Castelferrato", "Gagliato", "Gaiarine", "Gaiola", "Gairo", "Galati Mamertino", "Galatone", "Galbiate", "Galgagnano", "Gallese", "Galliate Lombardo", "Gallicano", "Gallicchio", "Galliera Veneta", "Gallio", "Gallo Matese", "Galluccio", "Galzignano Terme", "Gambara", "Gambasca", "Gambatesa", "Gamberale", "Gambolò", "Gandellino", "Gandosso", "Garaguso", "Garbagna Novarese", "Garbagnate Monastero", "Gardone Riviera", "Garessio", "Gargazzone", "Garlasco", "Garlenda", "Garzeno", "Gasperina", "Gattatico", "Gattico", "Gavardo", "Gavello", "Gavi", "Gavirate", "Gavorrano", "Gazzada Schianno", "Gazzo", "Gazzola", "Gela", "Gemona del Friuli", "Genazzano", "Genivolta", "Genoni", "Genuri", "Genzano di Roma", "Gera Lario", "Geraci Siculo", "Gerenzago", "Gergei", "Germagno", "Gerocarne", "Gerosa", "Gesico", "Gessopalena", "Gesualdo", "Ghemme", "Ghilarza", "Ghislarengo", "Giaglione", "Giano dell'Umbria", "Giardinello", "Giarole", "Giarre", "Giaveno", "Giba", "Gifflenga", "Giffoni Sei Casali", "Gignese", "Gildone", "Ginestra", "Ginosa", "Gioia dei Marsi", "Gioia Sannitica", "Gioiosa Ionica", "Giove", "Giovo", "Girifalco", "Gissi", "Giugliano in Campania", "Giuliano di Roma", "Giulianova", "Giungano", "Giussago", "Giustenice", "Giusvalla", "Gizzeria", "Godega di Sant'Urbano", "Godrano", "Golasecca", "Golfo Aranci", "Gonars", "Gonnesa", "Gonnosfanadiga", "Gonnostramatza", "Gordona", "Gorgo al Monticano", "Gorgonzola", "Gorizia", "Gorla Minore", "Gorle", "Gorno", "Gorreto", "Gosaldo", "Gottasecca", "Govone", "Gradara", "Grado", "Graffignana", "Graglia", "Gragnano Trebbiense", "Grana", "Granarolo dell'Emilia", "Grandate", "Graniti", "Grantola", "Granze", "Grassobbio", "Grauno", "Gravellona Lomellina", "Gravere", "Gravina in Puglia", "Grazzano Badoglio", "Greci", "Gremiasco", "Gressoney-la-Trinitè", "Greve in Chianti", "Grezzana", "Gricignano di Aversa", "Grigno", "Grimaldi", "Grisignano di Zocco", "Grizzana Morandi", "Gromo", "Grone", "Gropello Cairoli", "Groscavallo", "Grosotto", "Grosso", "Grottaglie", "Grottammare", "Grotte", "Grotteria", "Grottolella", "Grugliasco", "Grumello del Monte", "Grumes", "Grumo Nevano", "Guagnano", "Gualdo Cattaneo", "Gualtieri", "Guamaggiore", "Guarcino", "Guardabosone", "Guardavalle", "Guardia Lombardi", "Guardia Piemontese", "Guardiagrele", "Guardiaregia", "Guarene", "Guastalla", "Gubbio", "Guglionesi", "Guidonia Montecelio", "Guilmi", "Guspini", "Gussola", "Idro", "Igliano", "Illasi", "Imbersago", "Imola", "Impruneta", "Incisa in Val d'Arno", "Incudine", "Ingria", "Introbio", "Introdacqua", "Inverigo", "Inverso Pinasca", "Invorio", "Ionadi", "Irma", "Isasca", "Ischia", "Ischitella", "Isera", "Isili", "Isola d'Asti", "Isola del Giglio", "Isola del Liri", "Isola della Scala", "Isola di Capo Rizzuto", "Isola Dovarese", "Isola Sant'Antonio", "Isolabella", "Isole Tremiti", "Ispani", "Ispra", "Issime", "Issogne", "Itala", "Ittireddu", "Ivano-Fracena", "Izano", "Jelsi", "Jerago con Orago", "Jesi", "Jolanda di Savoia", "Joppolo Giancaxio", "La Cassa", "La Maddalena", "La Morra", "La Spezia", "La Valle", "Labico", "Lacchiarella", "Lacedonia", "Laconi", "Laerru", "Laghi", "Lagnasco", "Lagonegro", "Lagundo", "Lainate", "Laino Borgo", "Laion", "Lajatico", "Lama dei Peligni", "Lambrugo", "Lamon", "Lamporecchio", "Lana", "Landiona", "Langhirano", "Lanusei", "Lanzada", "Lanzo Torinese", "Lapio", "L'Aquila", "Lardaro", "Lari", "Larino", "Lasa", "Lasino", "Lastebasse", "Latera", "Laterza", "Latina", "Latronico", "Lauco", "Laureana di Borrello", "Laurenzana", "Lauriano", "Laurito", "Lavagna", "Lavarone", "Lavena Ponte Tresa", "Lavenone", "Lavis", "Lazzate", "Lecce nei Marsi", "Ledro", "Leggiuno", "Legnano", "Lei", "Leivi", "Lendinara", "Lenna", "Leno", "Lenta", "Lentella", "Lentini", "Leonforte", "Lequile", "Lequio Tanaro", "Lerici", "Lesa", "Lesignano de' Bagni", "Lesmo", "Lessona", "Letino", "Lettere", "Lettopalena", "Levate", "Levice", "Levone", "Liberi", "Licata", "Licenza", "Lierna", "Lignano Sabbiadoro", "Ligosullo", "Limana", "Limbadi", "Limena", "Limina", "Limone sul Garda", "Linarolo", "Lioni", "Lipomo", "Liscate", "Lisciano Niccone", "Lisio", "Liveri", "Livinallongo del Col di Lana", "Livo", "Livorno Ferraris", "Lizzanello", "Lizzano in Belvedere", "Loazzolo", "Locate di Triulzi", "Locatello", "Locorotondo", "Loculi", "Lodi", "Lodine", "Lograto", "Loiri Porto San Paolo", "Lomazzo", "Lombriasco", "Lona-Lases", "Lonate Pozzolo", "Londa", "Longare", "Longhena", "Longiano", "Longobucco", "Longone Sabino", "Loranzè", "Loreglia", "Lorenzana", "Loreto", "Loria", "Loro Piceno", "Losine", "Lovere", "Lozio", "Lozzo Atestino", "Lozzolo", "Lubriano", "Lucca Sicula", "Lucignano", "Lucito", "Lucoli", "Lugnacco", "Lugo", "Luino", "Lula", "Lumezzane", "Lunano", "Lungro", "Luogosanto", "Lurago d'Erba", "Lurano", "Lurate Caccivio", "Luserna", "Lusernetta", "Lusia", "Lusigliè", "Lustra", "Luzzana", "Luzzi", "Maccastorna", "Macchia Valfortore", "Macello", "Macerata Campania", "Macherio", "Macomer", "Macugnaga", "Madesimo", "Madone", "Maenza", "Magasa", "Maggiora", "Magione", "Magliano Alfieri", "Magliano de' Marsi", "Magliano in Toscana", "Magliano Sabina", "Maglie", "Maglione", "Magnago", "Magnano in Riviera", "Magrè sulla Strada del Vino", "Maida", "Maierato", "Maiolo", "Mairago", "Maissana", "Malagnino", "Malborghetto Valbruna", "Malè", "Maleo", "Maletto", "Malgesso", "Malito", "Malles Venosta", "Malo", "Malosco", "Malvagna", "Malvito", "Mamoiada", "Mandanici", "Mandatoriccio", "Mandello del Lario", "Manduria", "Manerbio", "Mango", "Maniace", "Manocalzati", "Mansuè", "Mantello", "Manzano", "Mapello", "Maracalagonis", "Marano di Napoli", "Marano Equo", "Marano Marchesato", "Marano sul Panaro", "Marano Vicentino", "Maratea", "Marcaria", "Marcellina", "Marcetelli", "Marchirolo", "Marciana Marina", "Marciano della Chiana", "Marcon", "Marene", "Marentino", "Margarita", "Margno", "Mariano Comense", "Marianopoli", "Marigliano", "Marineo", "Marlengo", "Marmentino", "Marmora", "Marone", "Marostica", "Marrubiu", "Marsala", "Marsico Nuovo", "Marta", "Martellago", "Martignacco", "Martignano", "Martinengo", "Martinsicuro", "Martirano Lombardo", "Martone", "Maruggio", "Marzano", "Marzano di Nola", "Marzio", "Masate", "Mascalucia", "Masciago Primo", "Masera", "Maserada sul Piave", "Masi Torello", "Maslianico", "Masone", "Massa d'Albe", "Massa e Cozzile", "Massa Fiscaglia", "Massa Lubrense", "Massa Martana", "Massalengo", "Massarosa", "Massello", "Massignano", "Massimino", "Massiola", "Matelica", "Mathi", "Matrice", "Mattinata", "Mazzano", "Mazzarino", "Mazzarrone", "Mazzin", "Meana di Susa", "Meda", "Medea", "Medicina", "Medolago", "Medolla", "Meduno", "Megliadino San Vitale", "Mel", "Melazzo", "Mele", "Melendugno", "Melfi", "Melicucco", "Melissa", "Melito di Napoli", "Melito Irpino", "Melle", "Melpignano", "Melzo", "Menarola", "Mendatica", "Menfi", "Meolo", "Merano", "Mercallo", "Mercatino Conca", "Mercato Saraceno", "Mercogliano", "Mergo", "Merì", "Merlino", "Mesagne", "Mesenzana", "Mesola", "Messina", "Meta", "Mezzago", "Mezzana Bigli", "Mezzana Rabattone", "Mezzanego", "Mezzanino", "Mezzegra", "Mezzocorona", "Mezzoldo", "Mezzomerico", "Miane", "Miazzina", "Miggiano", "Migliarino", "Miglierina", "Mignanego", "Milano", "Milena", "Milis", "Militello Rosmarino", "Milo", "Mineo", "Minerbio", "Minervino Murge", "Minturno", "Mioglia", "Mirabella Eclano", "Mirabello", "Mirabello Sannitico", "Miranda", "Mirano", "Misano Adriatico", "Misilmeri", "Missaglia", "Misterbianco", "Moasca", "Modena", "Modigliana", "Modugno", "Moggio", "Moglia", "Mogliano Veneto", "Mogoro", "Moimacco", "Moio de' Calvi", "Moiola", "Molare", "Molfetta", "Molinara", "Molini di Triora", "Molise", "Mollia", "Molteno", "Molvena", "Mombaldone", "Mombaroccio", "Mombasiglio", "Mombello Monferrato", "Momo", "Mompeo", "Monacilioni", "Monasterace", "Monastero di Lanzo", "Monasterolo Casotto", "Monasterolo di Savigliano", "Monastir", "Moncalvo", "Moncestino", "Monchio delle Corti", "Moncrivello", "Mondaino", "Mondolfo", "Mondragone", "Monesiglio", "Monforte d'Alba", "Monfumo", "Monghidoro", "Mongiardino Ligure", "Mongrando", "Monguelfo-Tesido", "Moniga del Garda", "Monno", "Monreale", "Monsampietro Morico", "Monsano", "Monserrato", "Montà", "Montacuto", "Montagano", "Montagna in Valtellina", "Montagnareale", "Montaguto", "Montalbano Elicona", "Montalcino", "Montaldo Bormida", "Montaldo Roero", "Montaldo Torinese", "Montalenghe", "Montalto delle Marche", "Montalto Dora", "Montalto Pavese", "Montanaro", "Montanera", "Montano Lucino", "Montaquila", "Montauro", "Monte Argentario", "Monte Cavallo", "Monte Colombo", "Monte Cremasco", "Monte di Procida", "Monte Grimano Terme", "Monte Marenzo", "Monte Porzio Catone", "Monte Roberto", "Monte San Biagio", "Monte San Giovanni Campano", "Monte San Giusto", "Monte San Pietrangeli", "Monte San Savino", "Monte Santa Maria Tiberina", "Monte Urano", "Monte Vidon Corrado", "Montebello di Bertona", "Montebello sul Sangro", "Montebelluna", "Montebuono", "Montecalvo Irpino", "Montecarlo", "Montecassiano", "Montecastrilli", "Montecatini-Terme", "Montecchio", "Montecchio Maggiore", "Montechiaro d'Acqui", "Montechiarugolo", "Montecilfone", "Montecorice", "Montecorvino Rovella", "Montecrestese", "Montedinove", "Montefalcione", "Montefalcone Appennino", "Montefalcone nel Sannio", "Montefelcino", "Montefiascone", "Montefiore Conca", "Montefiorino", "Monteforte Cilento", "Monteforte Irpino", "Montefranco", "Montefusco", "Montegalda", "Montegallo", "Montegiordano", "Montegranaro", "Montegrino Valtravaglia", "Montegrosso Pian Latte", "Monteiasi", "Montelanico", "Monteleone di Fermo", "Monteleone di Spoleto", "Monteleone Rocca Doria", "Montelepre", "Montella", "Montelongo", "Montelupo Albese", "Montelupone", "Montemaggiore Belsito", "Montemale di Cuneo", "Montemarciano", "Montemesola", "Montemignaio", "Montemilone", "Montemonaco", "Montemurro", "Montenero di Bisaccia", "Montenero Val Cocchiara", "Monteodorisio", "Monteparano", "Montepulciano", "Monterchi", "Montereale Valcellina", "Monteriggioni", "Monteroni d'Arbia", "Monterosi", "Monterosso Almo", "Monterosso Grana", "Monterotondo Marittimo", "Montesano Salentino", "Montesarchio", "Montescano", "Montescudaio", "Montese", "Montesilvano", "Monteu da Po", "Montevago", "Montevecchia", "Monteverde", "Monteviale", "Monti", "Monticelli Brusati", "Monticelli Pavese", "Monticello Conte Otto", "Montichiari", "Montieri", "Montignoso", "Montjovet", "Montoggio", "Montopoli di Sabina", "Montorfano", "Montorio nei Frentani", "Montoro Inferiore", "Montorso Vicentino", "Montresta", "Monvalle", "Monzambano", "Morano Calabro", "Moransengo", "Morazzone", "Morbello", "Morciano di Romagna", "Mordano", "Mores", "Moretta", "Morgano", "Morgongiori", "Moriago della Battaglia", "Morigerati", "Morino", "Morlupo", "Mornago", "Mornico al Serio", "Morolo", "Morra De Sanctis", "Morro d'Oro", "Morrone del Sannio", "Morsano al Tagliamento", "Mortara", "Morterone", "Moscazzano", "Mosciano Sant'Angelo", "Moso in Passiria", "Mossano", "Motta Baluffi", "Motta d'Affermo", "Motta di Livenza", "Motta San Giovanni", "Motta Sant'Anastasia", "Mottafollone", "Motteggiana", "Mozzagrogna", "Mozzate", "Mozzo", "Muggia", "Mugnano del Cardinale", "Mulazzano", "Mura", "Murazzano", "Murialdo", "Murlo", "Muro Lucano", "Muscoline", "Musile di Piave", "Mussolente", "Muzzana del Turgnano", "Nago-Torbole", "Nanno", "Napoli", "Narcao", "Nardodipace", "Naro", "Nasino", "Naturno", "Nave San Rocco", "Naz-Sciaves", "Ne", "Negrar", "Neive", "Nemi", "Neoneli", "Nereto", "Nervesa della Battaglia", "Nespolo", "Netro", "Neviano", "Neviglie", "Nibbiano", "Nibionno", "Nicolosi", "Nicosia", "Niella Belbo", "Nimis", "Nissoria", "Nizza Monferrato", "Noasca", "Nocciano", "Nocera Superiore", "Nocera Umbra", "Noci", "Noepoli", "Nogaredo", "Nogarole Vicentino", "Nola", "Noli", "Nomi", "None", "Noragugume", "Norcia", "Nosate", "Noto", "Nova Milanese", "Nova Siri", "Novaledo", "Novara", "Novate Mezzola", "Nove", "Novellara", "Noventa di Piave", "Noventa Vicentina", "Novi Ligure", "Noviglio", "Nucetto", "Nughedu Santa Vittoria", "Nulvi", "Nuoro", "Nuragus", "Nuraminis", "Nurri", "Nusco", "Nuvolera", "Occhieppo Inferiore", "Occhiobello", "Ocre", "Odalengo Piccolo", "Odolo", "Offagna", "Offida", "Oggebbio", "Oggiono", "Ogliastro Cilento", "Olcenengo", "Oleggio", "Olevano di Lomellina", "Olevano sul Tusciano", "Olgiate Molgora", "Olginate", "Oliva Gessi", "Oliveri", "Oliveto Lario", "Olivetta San Michele", "Ollastra", "Ollomont", "Olmeneta", "Olmo Gentile", "Oltressenda Alta", "Olzai", "Omegna", "Onanì", "Oncino", "Onifai", "Ono San Pietro", "Onzo", "Opi", "Oppido Lucano", "Ora", "Oratino", "Orbetello", "Orciano Pisano", "Ordona", "Orgiano", "Oria", "Origgio", "Orio al Serio", "Orio Litta", "Oriolo Romano", "Ormea", "Ornago", "Ornica", "Orotelli", "Orroli", "Orsara Bormida", "Orsenigo", "Orsomarso", "Orta Nova", "Ortacesus", "Ortelle", "Ortignano Raggiolo", "Ortona", "Ortonovo", "Ortucchio", "Orune", "Orvinio", "Orzivecchi", "Osasio", "Osidda", "Osilo", "Osini", "Osio Sotto", "Osnago", "Ospedaletti", "Ospedaletto d'Alpinolo", "Ospedaletto Lodigiano", "Ospitaletto", "Ossana", "Ossimo", "Ossuccio", "Ostellato", "Ostiglia", "Ostra Vetere", "Otranto", "Ottana", "Ottaviano", "Ottobiano", "Oulx", "Ovaro", "Ovindoli", "Oyace", "Ozieri", "Ozzano Monferrato", "Pabillonis", "Paceco", "Pachino", "Padenghe sul Garda", "Paderna", "Paderno del Grappa", "Paderno Franciacorta", "Padova", "Padru", "Paduli", "Paese", "Paganico Sabino", "Pagliara", "Pagnacco", "Pagnona", "Pago Veiano", "Paitone", "Palagano", "Palagiano", "Palaia", "Palata", "Palazzago", "Palazzo Canavese", "Palazzo San Gervasio", "Palazzolo dello Stella", "Palazzolo Vercellese", "Palena", "Palermo", "Palestro", "Palizzi", "Pallanzeno", "Palma Campania", "Palmanova", "Palmas Arborea", "Palmiano", "Palo del Colle", "Palombaro", "Palosco", "Palù del Fersina", "Paluzza", "Pancalieri", "Panchià", "Panettieri", "Pannarano", "Pantelleria", "Paola", "Papasidero", "Parabiago", "Paratico", "Parè", "Parenti", "Pareto", "Parlasco", "Parodi Ligure", "Parolise", "Parrano", "Partanna", "Paruzzaro", "Pasian di Prato", "Paspardo", "Passignano sul Trasimeno", "Pastena", "Pastrengo", "Pasturo", "Paternò", "Paternopoli", "Pattada", "Patù", "Paularo", "Paulilatino", "Paupisi", "Pavia", "Pavone Canavese", "Pavullo nel Frignano", "Peccioli", "Pecetto di Valenza", "Pecorara", "Pedara", "Pedavena", "Pederobba", "Pedivigliano", "Peglio", "Pegognaga", "Peio", "Pella", "Pellezzano", "Pellizzano", "Penango", "Penna San Giovanni", "Pennabilli", "Pennapiedimonte", "Pentone", "Perarolo di Cadore", "Percile", "Perdaxius", "Perego", "Perfugas", "Pergine Valsugana", "Perinaldo", "Perledo", "Perlo", "Pernumia", "Perosa Argentina", "Perrero", "Pertengo", "Pertica Bassa", "Pertusio", "Pesaro", "Pescantina", "Pescarolo ed Uniti", "Pescate", "Peschici", "Peschiera del Garda", "Pescina", "Pescocostanzo", "Pescopagano", "Pescorocchiano", "Pescosolido", "Pessina Cremonese", "Petacciato", "Petina", "Petralia Sottana", "Petrella Tifernina", "Petriolo", "Petrizzi", "Petrosino", "Pettenasco", "Pettineo", "Pettorano sul Gizio", "Peveragno", "Pezzaze", "Piacenza", "Piadena", "Piaggine", "Pian di Sco", "Piana degli Albanesi", "Piancastagnaio", "Piandimeleto", "Pianella", "Pianello Val Tidone", "Pianezza", "Pianfei", "Pianiga", "Pianopoli", "Piansano", "Piario", "Piateda", "Piazza al Serchio", "Piazza Brembana", "Piazzola sul Brenta", "Picciano", "Picinisco", "Piea", "Piedimonte Etneo", "Piedimonte San Germano", "Piegaro", "Pieranica", "Pietra Ligure", "Pietrabbondante", "Pietracamela", "Pietracupa", "Pietraferrazzana", "Pietragalla", "Pietramelara", "Pietranico", "Pietrapertosa", "Pietraporzio", "Pietrarubbia", "Pietrastornina", "Pietrelcina", "Pieve Albignola", "Pieve del Cairo", "Pieve di Cadore", "Pieve di Coriano", "Pieve di Teco", "Pieve Emanuele", "Pieve Fosciana", "Pieve Porto Morone", "Pieve Santo Stefano", "Pieve Torina", "Pievebovigliana", "Piglio", "Pignataro Interamna", "Pignola", "Pigra", "Pimentel", "Pinarolo Po", "Pincara", "Pineto", "Pino sulla Sponda del Lago Maggiore", "Pinzano al Tagliamento", "Piobbico", "Piobesi Torinese", "Pioltello", "Piombino Dese", "Piossasco", "Piove di Sacco", "Piovera", "Piozzo", "Pisa", "Piscina", "Pisciotta", "Pisoniano", "Pistoia", "Pitigliano", "Piuro", "Pizzale", "Pizzo", "Pizzoli", "Pizzoni", "Plataci", "Platì", "Plesio", "Plodio", "Pocenia", "Podenzano", "Poggiardo", "Poggio a Caiano", "Poggio Bustone", "Poggio Imperiale", "Poggio Moiano", "Poggio Picenze", "Poggio Rusco", "Poggio San Marcello", "Poggio Sannita", "Poggiofiorito", "Poggioreale", "Poggiridenti", "Pognana Lario", "Pogno", "Pojana Maggiore", "Polcenigo", "Polesine Parmense", "Polia", "Polignano a Mare", "Polino", "Polizzi Generosa", "Pollein", "Pollenza", "Pollina", "Pollutri", "Polpenazze del Garda", "Polverigi", "Pomaretto", "Pomaro Monferrato", "Pombia", "Pomigliano d'Arco", "Pompeiana", "Pomponesco", "Poncarale", "Ponna", "Ponso", "Pontboset", "Ponte", "Ponte dell'Olio", "Ponte di Piave", "Ponte in Valtellina", "Ponte nelle Alpi", "Ponte Nossa", "Ponte San Pietro", "Pontecagnano Faiano", "Pontechianale", "Pontecurone", "Pontedera", "Pontelatone", "Pontenure", "Pontestura", "Pontey", "Ponti sul Mincio", "Pontinia", "Pontirolo Nuovo", "Pontremoli", "Ponza", "Ponzano Monferrato", "Ponzano Veneto", "Popoli", "Porano", "Porcia", "Porlezza", "Porpetto", "Portacomaro", "Porte", "Portico di Caserta", "Portigliola", "Porto Ceresio", "Porto Empedocle", "Porto Recanati", "Porto Sant'Elpidio", "Porto Torres", "Porto Viro", "Portocannone", "Portofino", "Portomaggiore", "Portoscuso", "Portula", "Posina", "Possagno", "Posta Fibreno", "Postalesio", "Postua", "Potenza Picena", "Povegliano", "Poviglio", "Pozza di Fassa", "Pozzaglio ed Uniti", "Pozzilli", "Pozzol Groppo", "Pozzoleone", "Pozzomaggiore", "Pozzuoli", "Pozzuolo Martesana", "Pradamano", "Pragelato", "Praiano", "Prali", "Pralungo", "Pramollo", "Prarostino", "Prascorsano", "Prata Camportaccio", "Prata di Pordenone", "Prata Sannita", "Pratiglione", "Prato allo Stelvio", "Prato Sesia", "Pratola Serra", "Pravisdomini", "Prazzo", "Preci", "Predazzo", "Predore", "Preganziol", "Prelà", "Premariacco", "Premia", "Premolo", "Preone", "Prepotto", "Preseglie", "Presezzo", "Pressana", "Pretoro", "Prezza", "Priero", "Prignano sulla Secchia", "Priocca", "Priolo Gargallo", "Prizzi", "Procida", "Proserpio", "Provaglio d'Iseo", "Proves", "Prunetto", "Puglianello", "Pulfero", "Pumenengo", "Pusiano", "Putignano", "Quadri", "Qualiano", "Quaregna", "Quarna Sopra", "Quarona", "Quart", "Quarto d'Altino", "Quartucciu", "Quattordio", "Quero", "Quincinetto", "Quingentole", "Quinto di Treviso", "Quinto Vicentino", "Quistello", "Rabbi", "Racalmuto", "Raccuja", "Radda in Chianti", "Radicofani", "Raffadali", "Ragogna", "Ragusa", "Ramacca", "Ramponio Verna", "Ranco", "Ranica", "Ranzo", "Rapallo", "Rapolano Terme", "Rapone", "Rasun Anterselva", "Ravanusa", "Ravascletto", "Ravenna", "Raviscanina", "Rea", "Reana del Rojale", "Recale", "Recco", "Recoaro Terme", "Redondesco", "Refrontolo", "Reggello", "Reggio Emilia", "Reino", "Remanzacco", "Renate", "Renon", "Rescaldina", "Resiutta", "Retorbido", "Revere", "Revine Lago", "Rezzago", "Rezzo", "Rhemes-Notre-Dame", "Rho", "Rialto", "Riardo", "Ribordone", "Ricaldone", "Riccione", "Ricengo", "Riese Pio X", "Rieti", "Rifreddo", "Rignano Garganico", "Rigolato", "Rimasco", "Rimini", "Rio Marina", "Rio Saliceto", "Riola Sardo", "Riolunato", "Rionero in Vulture", "Ripa Teatina", "Ripacandida", "Ripalta Arpina", "Ripalta Guerina", "Ripatransone", "Ripe San Ginesio", "Riposto", "Riva del Garda", "Riva Ligure", "Riva Valdobbia", "Rivalta Bormida", "Rivamonte Agordino", "Rivara", "Rivarolo del Re ed Uniti", "Rivarone", "Rive", "Rivello", "Rivignano", "Rivodutri", "Rivoli Veronese", "Rizziconi", "Roana", "Roascio", "Roatto", "Robbiate", "Robecchetto con Induno", "Robecco Pavese", "Robella", "Roburent", "Rocca Canterano", "Rocca d'Arazzo", "Rocca de' Baldi", "Rocca D'Evandro", "Rocca di Cambio", "Rocca di Mezzo", "Rocca di Papa", "Rocca Imperiale", "Rocca Pia", "Rocca Priora", "Rocca San Felice", "Rocca Santa Maria", "Rocca Sinibalda", "Roccabascerana", "Roccabianca", "Roccacasale", "Roccafiorita", "Roccaforte del Greco", "Roccaforte Mondovì", "Roccafranca", "Roccagloriosa", "Roccalbegna", "Roccamandolfi", "Roccamonfina", "Roccamorice", "Roccantica", "Roccapiemonte", "Roccaraso", "Roccascalegna", "Roccasecca dei Volsci", "Roccasparvera", "Roccastrada", "Roccaverano", "Roccavione", "Roccella Ionica", "Rocchetta a Volturno", "Rocchetta di Vara", "Rocchetta Ligure", "Rocchetta Palafea", "Rocchetta Tanaro", "Roddi", "Rodello", "Rodengo Saiano", "Rodi Garganico", "Rodigo", "Rofrano", "Roggiano Gravina", "Rogliano", "Rogno", "Roiate", "Roisan", "Rolo", "Romagnano al Monte", "Romagnese", "Romana", "Romano Canavese", "Romano di Lombardia", "Rombiolo", "Romentino", "Ronago", "Roncade", "Roncaro", "Roncello", "Ronchi Valsugana", "Ronciglione", "Ronco Biellese", "Ronco Canavese", "Roncobello", "Roncofreddo", "Roncone", "Rondissone", "Ronzo-Chienis", "Roppolo", "Rosà", "Rosasco", "Rosazza", "Roscigno", "Rosello", "Roseto degli Abruzzi", "Rosignano Marittimo", "Rosolina", "Rosora", "Rossana", "Rossano Veneto", "Rosta", "Rota Greca", "Rotello", "Rotondella", "Rottofreno", "Roure", "Rovasenda", "Rovegno", "Rovello Porro", "Roverchiara", "Roverè Veronese", "Roveredo in Piano", "Rovescala", "Roviano", "Rovito", "Rozzano", "Rubiana", "Ruda", "Rueglio", "Ruffia", "Rufina", "Ruino", "Ruoti", "Rutigliano", "Ruviano", "Ruvo di Puglia", "Sabbia", "Sabbioneta", "Saccolongo", "Sacrofano", "Sagama", "Sagrado", "Saint-Christophe", "Saint-Marcel", "Saint-Oyen", "Saint-Rhémy-en-Bosses", "Sala Baganza", "Sala Bolognese", "Sala Consilina", "Salandra", "Salara", "Salassa", "Salcedo", "Sale", "Sale Marasino", "Salemi", "Salerano Canavese", "Salerno", "Salgareda", "Salice Salentino", "Salisano", "Salle", "Salò", "Salsomaggiore Terme", "Saltrio", "Saluggia", "Saluzzo", "Salvirola", "Salza di Pinerolo", "Salzano", "Samassi", "Sambuca di Sicilia", "Sambuci", "Sammichele di Bari", "Samolaco", "Samone", "Samugheo", "San Bartolomeo in Galdo", "San Basile", "San Bassano", "San Benedetto Belbo", "San Benedetto del Tronto", "San Benedetto Po", "San Benedetto Val di Sambro", "San Bernardino Verbano", "San Biagio di Callalta", "San Biagio Saracinisco", "San Bonifacio", "San Calogero", "San Canzian d'Isonzo", "San Casciano dei Bagni", "San Cassiano", "San Cesareo", "San Cesario sul Panaro", "San Chirico Raparo", "San Cipriano d'Aversa", "San Cipriano Po", "San Colombano al Lambro", "San Colombano Certenoli", "San Cosmo Albanese", "San Costantino Calabro", "San Cristoforo", "San Damiano d'Asti", "San Daniele del Friuli", "San Demetrio Corone", "San Didero", "San Donaci", "San Donato di Ninea", "San Donato Val di Comino", "San Fedele Intelvi", "San Felice a Cancello", "San Felice del Benaco", "San Felice sul Panaro", "San Ferdinando di Puglia", "San Fili", "San Fior", "San Floriano del Collio", "San Francesco al Campo", "San Gavino Monreale", "San Genesio Atesino", "San Gennaro Vesuviano", "San Germano dei Berici", "San Gervasio Bresciano", "San Giacomo delle Segnate", "San Giacomo Vercellese", "San Gimignano", "San Giorgio a Cremano", "San Giorgio Albanese", "San Giorgio del Sannio", "San Giorgio delle Pertiche", "San Giorgio di Mantova", "San Giorgio di Pesaro", "San Giorgio in Bosco", "San Giorgio La Molara", "San Giorgio Monferrato", "San Giorgio Piacentino", "San Giorgio su Legnano", "San Giovanni a Piro", "San Giovanni Bianco", "San Giovanni del Dosso", "San Giovanni Gemini", "San Giovanni in Croce", "San Giovanni in Galdo", "San Giovanni in Persiceto", "San Giovanni la Punta", "San Giovanni Lupatoto", "San Giovanni Suergiu", "San Giovanni Valdarno", "San Giuliano di Puglia", "San Giuliano Terme", "San Giuseppe Vesuviano", "San Giusto Canavese", "San Gregorio da Sassola", "San Gregorio d'Ippona", "San Gregorio Matese", "San Lazzaro di Savena", "San Leonardo", "San Leucio del Sannio", "San Lorenzo", "San Lorenzo Bellizzi", "San Lorenzo di Sebato", "San Lorenzo in Campo", "San Lorenzo Maggiore", "San Luca", "San Lupo", "San Mango Piemonte", "San Marcellino", "San Marcello Pistoiese", "San Marco D'Alunzio", "San Marco Evangelista", "San Marco la Catola", "San Martino Alfieri", "San Martino Canavese", "San Martino dall'Argine", "San Martino di Finita", "San Martino di Venezze", "San Martino in Passiria", "San Martino in Rio", "San Martino Sannita", "San Martino sulla Marrucina", "San Marzano di San Giuseppe", "San Marzano sul Sarno", "San Maurizio Canavese", "San Mauro Castelverde", "San Mauro di Saline", "San Mauro La Bruca", "San Mauro Pascoli", "San Michele al Tagliamento", "San Michele di Ganzaria", "San Michele Mondovì", "San Miniato", "San Nazzaro", "San Nazzaro Val Cavargna", "San Nicola Arcella", "San Nicola da Crissa", "San Nicola la Strada", "San Nicolò d'Arcidano", "San Nicolò Gerrei", "San Pancrazio Salentino", "San Paolo Albanese", "San Paolo Cervo", "San Paolo di Civitate", "San Paolo Solbrito", "San Pier d'Isonzo", "San Piero a Sieve", "San Pietro a Maida", "San Pietro al Tanagro", "San Pietro Avellana", "San Pietro di Cadore", "San Pietro di Feletto", "San Pietro in Amantea", "San Pietro in Casale", "San Pietro in Gu", "San Pietro in Lama", "San Pietro Mosezzo", "San Pietro Val Lemina", "San Pietro Viminario", "San Polo dei Cavalieri", "San Polo di Piave", "San Ponso", "San Potito Sannitico", "San Prisco", "San Prospero", "San Quirino", "San Roberto", "San Romano in Garfagnana", "San Salvatore di Fitalia", "San Salvatore Telesino", "San Sebastiano al Vesuvio", "San Sebastiano da Po", "San Secondo Parmense", "San Severino Marche", "San Siro", "San Sostene", "San Sperate", "San Teodoro", "San Tomaso Agordino", "San Valentino Torio", "San Vendemiano", "San Vincenzo", "San Vincenzo Valle Roveto", "San Vito", "San Vito al Torre", "San Vito dei Normanni", "San Vito di Fagagna", "San Vito Lo Capo", "San Vito sullo Ionio", "San Vittore Olona", "San Zeno Naviglio", "San Zenone al Po", "Sanarica", "Sandrigo", "Sanfront", "Sangiano", "Sanguinetto", "Sannazzaro de' Burgondi", "Sannicola", "Sansepolcro", "Santa Caterina Albanese", "Santa Caterina Villarmosa", "Santa Cristina d'Aspromonte", "Santa Cristina Gela", "Santa Croce Camerina", "Santa Croce di Magliano", "Santa Domenica Talao", "Santa Elisabetta", "Santa Flavia", "Santa Giusta", "Santa Giustina in Colle", "Santa Lucia del Mela", "Santa Lucia di Serino", "Santa Margherita di Belice", "Santa Margherita Ligure", "Santa Maria a Vico", "Santa Maria Coghinas", "Santa Maria del Molise", "Santa Maria di Licodia", "Santa Maria Hoè", "Santa Maria la Carità", "Santa Maria La Longa", "Santa Maria Nuova", "Santa Marina Salina", "Santa Ninfa", "Santa Severina", "Santa Sofia D'Epiro", "Santa Teresa Gallura", "Santa Vittoria d'Alba", "Santadi", "Sant'Agata Bolognese", "Sant'Agata del Bianco", "Sant'Agata di Militello", "Sant'Agata Feltria", "Sant'Agata Li Battiati", "Sant'Agnello", "Sant'Albano Stura", "Sant'Alessio in Aspromonte", "Sant'Alfio", "Sant'Ambrogio di Valpolicella", "Sant'Anastasia", "Sant'Andrea Apostolo dello Ionio", "Sant'Andrea di Conza", "Sant'Angelo a Cupolo", "Sant'Angelo a Scala", "Sant'Angelo d'Alife", "Sant'Angelo del Pesco", "Sant'Angelo di Piove di Sacco", "Sant'Angelo in Pontano", "Sant'Angelo Le Fratte", "Sant'Angelo Lodigiano", "Sant'Angelo Muxaro", "Sant'Anna Arresi", "Sant'Antimo", "Sant'Antonino di Susa", "Sant'Antonio di Gallura", "Sant'Arcangelo", "Sant'Arcangelo Trimonte", "Sant'Arsenio", "Sant'Egidio alla Vibrata", "Sant'Elena", "Sant'Elia a Pianisi", "Sant'Elpidio a Mare", "Santeramo in Colle", "Sant'Eufemia d'Aspromonte", "Sant'Eusanio Forconese", "Santi Cosma e Damiano", "Sant'Ilario d'Enza", "Santo Stefano al Mare", "Santo Stefano d'Aveto", "Santo Stefano di Cadore", "Santo Stefano di Magra", "Santo Stefano di Sessanio", "Santo Stefano Lodigiano", "Santo Stefano Roero", "Santo Stino di Livenza", "Santomenna", "Sant'Omobono Terme", "Santopadre", "Santorso", "Santu Lussurgiu", "Sanza", "Saonara", "Sappada", "Saracena", "Sarcedo", "Sardara", "Sarego", "Sarezzano", "Sarmato", "Sarnano", "Sarno", "Saronno", "Sarroch", "Sarteano", "Sarule", "Sassano", "Sassello", "Sassinoro", "Sasso Marconi", "Sassofeltrio", "Sassuolo", "Satriano di Lucania", "Sauze di Cesana", "Sava", "Saviano", "Savignano Irpino", "Savignano sul Rubicone", "Savignone", "Savoca", "Savogna d'Isonzo", "Savona", "Scafati", "Scala", "Scaldasole", "Scalenghe", "Scampitella", "Scandiano", "Scandolara Ravara", "Scandriglia", "Scano di Montiferro", "Scanzano Jonico", "Scapoli", "Scarmagno", "Scarperia", "Scerni", "Scheggino", "Schiavon", "Schilpario", "Schivenoglia", "Sciara", "Scido", "Scilla", "Sciolze", "Sclafani Bagni", "Scopa", "Scoppito", "Scorrano", "Scurcola Marsicana", "Scurzolengo", "Secinaro", "Secugnago", "Sedico", "Sedini", "Sedrina", "Segariu", "Segni", "Segrate", "Selargius", "Selegas", "Sellero", "Sellia Marina", "Selva di Cadore", "Selva di Val Gardena", "Selve Marcone", "Semestene", "Seminara", "Senago", "Senale-San Felice", "Senerchia", "Senigallia", "Senise", "Senna Lodigiana", "Sennori", "Sepino", "Sequals", "Serdiana", "Seren del Grappa", "Seriate", "Serino", "Sermide", "Sernaglia della Battaglia", "Serole", "Serra de' Conti", "Serra Riccò", "Serra San Quirico", "Serracapriola", "Serralunga d'Alba", "Serramanna", "Serramezzana", "Serrapetrona", "Serrastretta", "Serravalle a Po", "Serravalle Langhe", "Serravalle Scrivia", "Serre", "Serri", "Serrungarina", "Servigliano", "Sessa Cilento", "Sessano del Molise", "Sestino", "Sesto al Reghena", "Sesto Campano", "Sesto Fiorentino", "Sestola", "Sestriere", "Settala", "Settime", "Settimo Rottaro", "Settimo Torinese", "Settingiano", "Seui", "Seveso", "Sezze", "Sgonico", "Siamaggiore", "Siano", "Sicignano degli Alburni", "Siddi", "Siena", "Signa", "Silanus", "Siligo", "Silius", "Sillavengo", "Silvano Pietra", "Simala", "Simbario", "Sinagra", "Sindia", "Sinio", "Sinnai", "Siracusa", "Siris", "Sirolo", "Siror", "Sissa", "Siziano", "Sluderno", "Smerillo", "Socchieve", "Sogliano al Rubicone", "Soglio", "Solagna", "Solaro", "Solarolo Rainerio", "Solbiate", "Solbiate Olona", "Soleminis", "Solesino", "Solferino", "Solignano", "Solonghello", "Solto Collina", "Somaglia", "Somma Lombardo", "Sommacampagna", "Sommariva Perno", "Sommo", "Soncino", "Sondrio", "Sonico", "Soprana", "Soraga", "Sorano", "Sorbo Serpico", "Sordevolo", "Soresina", "Sorgono", "Sorianello", "Soriano nel Cimino", "Soriso", "Sormano", "Sorrento", "Sortino", "Sospirolo", "Sostegno", "Sover", "Sovere", "Soveria Simeri", "Sovicille", "Sovizzo", "Sozzago", "Spadola", "Sparone", "Spello", "Sperlinga", "Sperone", "Spezzano Albanese", "Spezzano Piccolo", "Spigno Monferrato", "Spilamberto", "Spilinga", "Spinazzola", "Spineda", "Spineto Scrivia", "Spino d'Adda", "Spinoso", "Spoleto", "Spongano", "Sporminore", "Spresiano", "Squillace", "Staffolo", "Staiti", "Stanghella", "Statte", "Stazzema", "Stefanaconi", "Stella Cilento", "Stelvio", "Sternatia", "Stia", "Stigliano", "Stilo", "Stintino", "Stornara", "Storo", "Stradella", "Strambino", "Stregna", "Stresa", "Striano", "Strona", "Strongoli", "Stroppo", "Sturno", "Subbiano", "Succivo", "Suelli", "Suisio", "Sulmona", "Sumirago", "Suni", "Supersano", "Surano", "Susa", "Sustinente", "Sutri", "Suvereto", "Taceno", "Taggia", "Taglio di Po", "Taibon Agordino", "Taio", "Talamello", "Talana", "Talla", "Tambre", "Tarano", "Tarantasca", "Tarcento", "Tarsia", "Tarvisio", "Tassarolo", "Taurano", "Taurianova", "Tavagnacco", "Tavarnelle Val di Pesa", "Tavenna", "Tavernerio", "Tavernole sul Mella", "Tavigliano", "Tavullia", "Teano", "Teglio", "Telese Terme", "Telti", "Telve di Sopra", "Temù", "Tenno", "Teor", "Teramo", "Terelle", "Terenzo", "Terlago", "Terlizzi", "Termeno sulla Strada del Vino", "Termoli", "Ternengo", "Terno d'Isola", "Terragnolo", "Terranova da Sibari", "Terranova di Pollino", "Terranuova Bracciolini", "Terrassa Padovana", "Terrazzo", "Terricciola", "Tertenia", "Terzo", "Terzolas", "Tesero", "Tessennano", "Teti", "Teverola", "Thiene", "Tiana", "Ticineto", "Tiglieto", "Tignale", "Tione degli Abruzzi", "Tirano", "Tiriolo", "Tissi", "Tivoli", "Toano", "Tocco da Casauria", "Todi", "Toirano", "Tolfa", "Tollo", "Tolve", "Ton", "Tonara", "Tonengo", "Tora e Piccilli", "Torano Nuovo", "Torcegno", "Torchiarolo", "Torella del Sannio", "Torgnon", "Torino di Sangro", "Torlino Vimercati", "Tornareccio", "Tornimparte", "Tornolo", "Torpè", "Torralba", "Torrazza Piemonte", "Torre Annunziata", "Torre Boldone", "Torre Cajetani", "Torre d'Arese", "Torre de' Negri", "Torre de' Picenardi", "Torre del Greco", "Torre di Ruggiero", "Torre d'Isola", "Torre Mondovì", "Torre Pallavicina", "Torre San Giorgio", "Torre Santa Susanna", "Torrebelvicino", "Torrecuso", "Torregrotta", "Torrenova", "Torretta", "Torrevecchia Teatina", "Torri di Quartesolo", "Torriana", "Torricella", "Torricella in Sabina", "Torricella Sicura", "Torriglia", "Torrioni", "Torrita Tiberina", "Tortona", "Tortorella", "Tortorici", "Toscolano-Maderno", "Tovo di Sant'Agata", "Trabia", "Tramatza", "Tramonti", "Tramonti di Sotto", "Trana", "Transacqua", "Trapani", "Trarego Viggiona", "Trasaghis", "Tratalias", "Travacò Siccomario", "Travedona-Monate", "Traversetolo", "Travesio", "Trebaseleghe", "Trecasali", "Trecastagni", "Trecchina", "Tredozio", "Tregnago", "Treiso", "Tremestieri Etneo", "Tremosine", "Trentinara", "Trentola-Ducenta", "Treppo Carnico", "Trepuzzi", "Tres", "Trescore Balneario", "Tresigallo", "Tresnuraghes", "Trevi", "Trevico", "Trevignano", "Treville", "Treviso", "Trezzano Rosa", "Trezzo sull'Adda", "Trezzone", "Tribiano", "Tricarico", "Tricerro", "Trichiana", "Trieste", "Trigolo", "Trinità d'Agultu e Vignola", "Trino", "Tripi", "Trissino", "Trivento", "Trivigliano", "Trivigno", "Trodena nel parco naturale", "Troia", "Tromello", "Tronzano Lago Maggiore", "Tropea", "Truccazzano", "Tuenno", "Tufillo", "Tufo", "Tuili", "Tuoro sul Trasimeno", "Turano Lodigiano", "Turbigo", "Turri", "Turrivalignani", "Tusa", "Ubiale Clanezzo", "Ucria", "Ugento", "Uggiate-Trevano", "Ulassai", "Umbertide", "Urago d'Oglio", "Urbana", "Urbe", "Urbisaglia", "Uri", "Urzulei", "Usellus", "Usmate Velate", "Ussaramanna", "Usseaux", "Ussita", "Uta", "Vaccarizzo Albanese", "Vacri", "Vado Ligure", "Vaglia", "Vaglio Serra", "Vaiano Cremasco", "Vailate", "Vajont", "Val di Nizza", "Val Masino", "Valbondione", "Valbrevenna", "Valda", "Valdaora", "Valdengo", "Valdidentro", "Valdina", "Valdobbiadene", "Valeggio", "Valentano", "Valenzano", "Valfabbrica", "Valfloriana", "Valganna", "Valgoglio", "Valgreghentino", "Valguarnera Caropepe", "Vallanzengo", "Vallata", "Valle Aurina", "Valle dell'Angelo", "Valle di Casies", "Valle Lomellina", "Valle Salimbene", "Vallebona", "Vallecrosia", "Valledoria", "Vallelonga", "Vallemaio", "Vallerano", "Vallerotonda", "Valleve", "Vallinfreda", "Vallo della Lucania", "Vallo Torinese", "Valmacca", "Valmala", "Valmorea", "Valnegra", "Valperga", "Valsavarenche", "Valsinni", "Valstagna", "Valtopina", "Valtournenche", "Valvasone", "Valverde", "Vandoies", "Vanzago", "Vaprio d'Adda", "Varallo", "Varano Borghi", "Varapodio", "Varco Sabino", "Varena", "Varese", "Varisella", "Varna", "Varzi", "Vas", "Vasia", "Vastogirardi", "Vauda Canavese", "Vazzola", "Vedano al Lambro", "Veddasca", "Vedeseta", "Veggiano", "Veglio", "Veleso", "Velletri", "Velo d'Astico", "Velturno", "Venaria Reale", "Venasca", "Vendone", "Venegono Inferiore", "Venetico", "Veniano", "Venticano", "Ventimiglia di Sicilia", "Venzone", "Verano Brianza", "Verbicaro", "Verceia", "Vercurago", "Verdello", "Verderio Superiore", "Vergato", "Verghereto", "Vermezzo", "Vernante", "Vernate", "Vernio", "Verolanuova", "Verolengo", "Verona", "Verrayes", "Verretto", "Verrua Po", "Vertemate con Minoprio", "Verucchio", "Vervio", "Verzegnis", "Verzuolo", "Vescovato", "Vespolate", "Vestenanova", "Vestone", "Vetralla", "Vezza d'Alba", "Vezzano", "Vezzano sul Crostolo", "Viadana", "Viagrande", "Vialfrè", "Viareggio", "Vibo Valentia", "Vicalvi", "Vicchio", "Vico Canavese", "Vico Equense", "Vicoforte", "Vicolungo", "Vicovaro", "Vidigulfo", "Vidracco", "Vietri di Potenza", "Viganella", "Vigano San Martino", "Vigasio", "Viggianello", "Viggiù", "Vigliano Biellese", "Vignale Monferrato", "Vignate", "Vignola-Falesina", "Vignolo", "Vigo di Cadore", "Vigo Rendena", "Vigolo", "Vigolzone", "Vigonovo", "Viguzzolo", "Villa Bartolomea", "Villa Biscossi", "Villa Castelli", "Villa Collemandina", "Villa d'Adda", "Villa del Bosco", "Villa di Briano", "Villa di Serio", "Villa d'Ogna", "Villa Faraldi", "Villa Lagarina", "Villa Literno", "Villa Poma", "Villa San Giovanni", "Villa San Pietro", "Villa Santa Lucia", "Villa Santa Maria", "Villa Sant'Antonio", "Villa Santo Stefano", "Villa Vicentina", "Villabate", "Villacidro", "Villadose", "Villafalletto", "Villafranca di Verona", "Villafranca Padovana", "Villafranca Sicula", "Villafrati", "Villagrande Strisaili", "Villalba", "Villalvernia", "Villamaina", "Villamarzana", "Villamiroglio", "Villanova Biellese", "Villanova d'Albenga", "Villanova d'Asti", "Villanova del Ghebbo", "Villanova di Camposampiero", "Villanova Mondovì", "Villanova Monteleone", "Villanova sull'Arda", "Villanova Tulo", "Villanovafranca", "Villanuova sul Clisi", "Villapiana", "Villar Dora", "Villar Pellice", "Villar San Costanzo", "Villarboit", "Villaricca", "Villarosa", "Villasanta", "Villasor", "Villastellone", "Villaurbana", "Villaverla", "Villesse", "Villette", "Villongo", "Vilminore di Scalve", "Vimodrone", "Vinchiaturo", "Vinci", "Vinzaglio", "Vione", "Virgilio", "Visano", "Visciano", "Visone", "Vistarino", "Vita", "Viticuso", "Vitorchiano", "Vittorio Veneto", "Vittuone", "Vitulazio", "Vivaro", "Viverone", "Vizzola Ticino", "Vo'", "Vobbia", "Vodo Cadore", "Voghiera", "Volano", "Volongo", "Volpara", "Volpeglino", "Volta Mantovana", "Voltago Agordino", "Voltido", "Volturara Irpina", "Volvera", "Zaccanopoli", "Zagarise", "Zambana", "Zandobbio", "Zanica", "Zavattarello", "Zeddiani", "Zelo Buon Persico", "Zeme", "Zenson di Piave", "Zerbo", "Zerfaliu", "Zermeghedo", "Zevio", "Ziano Piacentino", "Zibido San Giacomo", "Zimella", "Zinasco", "Zocca", "Zola Predosa", "Zollino", "Zoppè di Cadore", "Zovencedo", "Zuccarello", "Zugliano", "Zumaglia", "Zungoli"};
    do {
      citta = name[random.nextInt(name.length)];
    } while (!(citta.matches(Seduta.CITTA_REGEX)));
    return citta;
  }

  /**
   * Metodo che genera un indirizzo causale.
   *
   * @return che genera un indirizzo casuale
   */
  public static String generateIndirizzo() {
    String via;
    do {
      via = generateLuogoDiNascita() + " Via "
              + generateNomeOCognome().toLowerCase(Locale.ROOT) + " " + randBetween(1, 99);
    } while (!(via.matches(Seduta.INDIRIZZO_REGEX)));
    return via;
  }

  /**
   * Metodo che genera una data per una seduta > della data corrente.
   *
   * @return una data per una seduta > della data corrente
   */
  public static String generateDataInCuiAvveraLaSeduta() {
    String test;
    do {
      Random randomDays = ThreadLocalRandom.current();
      LocalDateTime date = LocalDateTime.now().plusDays(randomDays.nextInt(365) + 1);
      test = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    } while (!(test.matches(Seduta.DATA_SEDUTA_REGEX)));
    return test;
  }

  /**
   * Metodo che restituisce un gruppo sanguigno a caso.
   *
   * @return restituisce un gruppo sanguigno a caso
   */
  public static String generateGruppoSanguigno() {
    String toReturn;
    Random random = new Random();
    String[] gruppo = {"0+", "0-", "A+", "A-", "B+" + "B-", "AB+", "AB-"};
    do {
      toReturn = gruppo[random.nextInt(gruppo.length)];
    } while (!(toReturn.matches(Guest.REG_GRUPPOSANGUIGNO)));
    return toReturn;

  }

  /**
   * Metodo che restituisce una patologia a caso.
   *
   * @return restituisce una patologia a caso
   */
  public static String generatePatologie() {
    String toReturn;
    Random random = new Random();
    String[] patologie = {"ARTRITE REUMATOIDE", "ARTRITI INFETTIVE", "ARTROSI",
            "ATEROSCLEROSI E ARTERIOSCLEROSI", "CEFALEA", "DEGENERAZIONE MACULARE SENILE",
            "DERMATITI", "DIABETE MELLITO ", "DISFUNZIONE ERETTILE",
            "GASTRITE E ULCERA PEPTICA", "IL DOLORE", "INCONTINENZA URINARIA",
            "IPERCIFOSI O DORSO CURVO (MALATTIA DI SCHEUERMANN)",
            "IPERTENSIONE ARTERIOSA", "MALATTIA DA REFLUSSO GASTROESOFAGEO",
            "MALATTIA DI ALZHEIMER", "MALATTIA DI HUNTINGTON", "MALATTIA DI PARKINSON",
            "MALATTIA DIVERTICOLARE", "MENINGITE", "OSTEOPOROSI", "PARAMORFISMI",
            "SCHIZOFRENIA", "SCOLIOSI", "SINDROME DI DOWN", "SINDROME INFLUENZALE",
            "SINDROMI CORONARICHE ACUTE (SCA)", "SINUSITI", "STERILITÀ",
            "TUMORE AL FEGATO", "TUMORE ALLA MAMMELLA", "TUMORE ALLA PROSTATA"};
    do {
      toReturn = patologie[random.nextInt(patologie.length)];
    } while (!(toReturn.matches(Guest.REG_PATOLOGIE)));
    return toReturn;

  }

  /**
   * Metodo che genera un numero di telefono casuale.
   *
   * @return numero di telefono casuale
   */
  public static String generateNumeroDiTelefono() {
    Random rand = new Random();
    String numero;
    do {
      numero = "";
      if (rand.nextBoolean()) {
        numero += "+" + randBetween(10, 99) + " ";
      }
      int num1, num2, num3;
      num1 = rand.nextInt(900) + 100;
      num2 = rand.nextInt(643) + 100;
      num3 = rand.nextInt(9000) + 1000;
      numero += String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);
    } while (!(numero.matches(Guest.NUMERO_TELEFONO)));

    return numero;
  }

  /**
   * Metodo che ritorna un commune  casuale
   *
   * @return genera un luogo di nascita casuale
   */


  /*
  public static String generateLuogoDiNascita() {
    Random random = new Random();
    String comune;
    String[] name =
            {"Abano Terme", "Abbadia Lariana", "Abbasanta", "Abbiategrasso",
                    "Abriola", "Accadia", "Accettura", "Accumoli", "Acerno",
                    "Aci Bonaccorsi", "Aci Catena", "Acireale", "Acquafondata",
                    "Acquafredda", "Acquanegra Cremonese", "Acquapendente",
                    "Acquarica del Capo", "Acquasanta Terme", "Acquaviva Collecroce",
                    "Acquaviva d'Isernia", "Acquaviva Platani", "Acqui Terme",
                    "Acuto", "Adrano", "Adrara San Rocco", "Adro", "Affile",
                    "Africo", "Agerola", "Agira", "Agliano Terme", "Aglientu",
                    "Agnadello", "Agnone", "Agordo", "Agra", "Agrate Conturbia",
                    "Agropoli", "Agugliaro", "Aidomaggiore", "Aielli", "Aiello del Friuli",
                    "Aieta", "Ailoche", "Airola", "Airuno", "Ala", "Ala di Stura",
                    "Alagna Valsesia", "Alano di Piave", "Alatri", "Alba Adriatica",
                    "Albairate", "Albano di Lucania", "Albano Sant'Alessandro",
                    "Albaredo Arnaboldi", "Albaredo per San Marco", "Albaretto della Torre",
                    "Albenga", "Alberobello", "Albese con Cassano", "Albi", "Albiano d'Ivrea",
                    "Albidona", "Albinea", "Albiolo", "Albissola Marina", "Albonese",
                    "Albugnano", "Alcamo", "Aldeno", "Ales", "Alessandria del Carretto",
                    "Alessano", "Alfano", "Alfianello", "Alfonsine", "Algua", "Alì Terme",
                    "Aliano", "Alice Castello", "Alife", "Aliminusa", "Alleghe", "Allerona",
                    "Allumiere", "Almè", "Almenno San Salvatore", "Alonte", "Alpignano",
                    "Alserio", "Altare", "Altavilla Milicia", "Altavilla Silentina",
                    "Altidona", "Altino", "Altivole", "Altofonte", "Altopascio", "Alvignano",
                    "Alzano Lombardo", "Alzate Brianza", "Amandola", "Amaro", "Amaseno",
                    "Amatrice", "Amblar", "Amelia", "Ameno", "Ampezzo", "Anagni", "Ancona",
                    "Andalo", "Andezeno", "Andorno Micca", "Andrate", "Andretta", "Andriano",
                    "Anfo", "Anghiari", "Angolo Terme", "Angrogna", "Anguillara Veneta",
                    "Annone di Brianza", "Anoia", "Anterivo", "Anticoli Corrado", "Antillo",
                    "Antrodoco", "Anversa degli Abruzzi", "Anzano di Puglia", "Anzio",
                    "Anzola d'Ossola", "Apecchio", "Apiro", "Appiano Gentile", "Appignano",
                    "Aprica", "Apricena", "Aprilia", "Aquila d'Arroscia", "Aquilonia", "Aradeo",
                    "Aramengo", "Arborea", "Arbus", "Arce", "Arcevia", "Arcidosso", "Arcisate",
                    "Arcola", "Arconate", "Arcugnano", "Ardauli", "Ardenno", "Ardore", "Arena Po",
                    "Arese", "Argegno", "Argenta", "Arguello", "Ari", "Ariano nel Polesine",
                    "Arielli", "Arignano", "Arizzano", "Arluno", "Armento", "Armungia", "Arnara",
                    "Arnesano", "Arona", "Arpaia", "Arpino", "Arquà Polesine", "Arquata Scrivia",
                    "Arrone", "Arsiè", "Arsita", "Arta Terme", "Artena", "Arvier", "Arzago d'Adda",
                    "Arzano", "Arzergrande", "Ascea", "Ascoli Piceno", "Ascrea", "Asigliano Veneto",
                    "Asola", "Assago", "Assisi", "Assolo", "Asti", "Ateleta", "Atena Lucana", "Atina",
                    "Atri", "Attigliano", "Atzara", "Augusta", "Aulla", "Aurigo", "Ausonia", "Avegno",
                    "Avella", "Averara", "Avetrana", "Aviano", "Avigliana", "Avigliano Umbro", "Avise",
                    "Avolasca", "Aymavilles", "Azzanello", "Azzano Decimo", "Azzano San Paolo",
                    "Azzio", "Baceno", "Badalucco", "Badia", "Badia Pavese", "Badia Tedalda",
                    "Bagaladi", "Bagnacavallo", "Bagnara di Romagna", "Bagnaria Arsa", "Bagnatica",
                    "Bagno a Ripoli", "Bagnoli del Trigno", "Bagnoli Irpino", "Bagnolo del Salento",
                    "Bagnolo in Piano", "Bagnolo Piemonte", "Bagnone", "Bagolino", "Baiano", "Baiso",
                    "Balangero", "Baldissero Canavese", "Baldissero Torinese", "Balestrino", "Ballao",
                    "Balmuccia", "Balsorano", "Balzola", "Banchette", "Banzi", "Baradili", "Baranello",
                    "Baranzate", "Baratili San Pietro", "Barbara", "Barbarano Vicentino", "Barbariga",
                    "Barberino di Mugello", "Barbianello", "Barbona", "Barchi", "Bard", "Bardi",
                    "Bardolino", "Bareggio", "Baressa", "Barga", "Barge", "Bari", "Bariano",
                    "Barile", "Barlassina", "Barni", "Barone Canavese", "Barrafranca", "Barrea",
                    "Barzago", "Barzanò", "Basaluzzo", "Baschi", "Baselga di Pinè", "Basiano",
                    "Basiglio", "Bassano Bresciano", "Bassano in Teverina", "Bassiano",
                    "Bastia Mondovì", "Bastida de' Dossi", "Bastiglia", "Battifollo",
                    "Battuda", "Bauladu", "Baveno", "Bedero Valcuvia", "Bedollo", "Bedulita",
                    "Beinasco", "Belcastro", "Belforte all'Isauro", "Belforte Monferrato",
                    "Belgirate", "Bellagio", "Bellante", "Bellegra", "Bellinzago Lombardo",
                    "Bellizzi", "Bellosguardo", "Bellusco", "Belmonte Castello",
                    "Belmonte in Sabina", "Belmonte Piceno", "Belsito", "Belvedere Langhe",
                    "Belvedere Ostrense", "Belvì", "Bene Lario", "Benestare", "Benevello",
                    "Benna", "Berbenno", "Berceto", "Beregazzo con Figliaro", "Bergamasco",
                    "Bergantino", "Bergolo", "Bernalda", "Bernate Ticino", "Berra", "Bertinoro",
                    "Bertonico", "Berzano di Tortona", "Berzo Inferiore", "Besana in Brianza",
                    "Besate", "Besenzone", "Besozzo", "Bettola", "Beura-Cardezza", "Beverino",
                    "Biancavilla", "Bianco", "Biandronno", "Bianzè", "Biassono", "Bibbiena",
                    "Bibiana", "Bicinicco", "Biella", "Bieno", "Bigarello", "Binasco",
                    "Bioglio", "Bione", "Bisaccia", "Bisceglie", "Bisenti", "Bistagno",
                    "Bitetto", "Bitritto", "Bivona", "Bizzarone", "Blello", "Blessagno",
                    "Blufi", "Bobbio", "Boca", "Boccioleto", "Bodio Lomnago", "Boffalora Sopra Ticino",
                    "Bognanco", "Boissano", "Bolano", "Bolgare", "Bollengo", "Bolognano",
                    "Bolognola", "Bolsena", "Bolzano", "Bolzano Vicentino", "Bomba", "Bompietro",
                    "Bonarcado", "Bonate Sopra", "Bonavigo", "Bondo", "Bonea", "Bonemerse",
                    "Bonito", "Bono", "Bonvicino", "Borca di Cadore", "Bordighera", "Bore",
                    "Borgarello", "Borgetto", "Borghetto di Borbera", "Borghetto Lodigiano",
                    "Borghi", "Borgiallo", "Borgo a Mozzano", "Borgo di Terzo", "Borgo Priolo",
                    "Borgo San Giacomo", "Borgo San Lorenzo", "Borgo San Siro", "Borgo Tossignano",
                    "Borgo Valsugana", "Borgo Vercelli", "Borgofranco d'Ivrea", "Borgolavezzaro",
                    "Borgomanero", "Borgomasino", "Borgonovo Val Tidone", "Borgoratto Mormorolo",
                    "Borgorose", "Borgosesia", "Bormio", "Borno", "Borore", "Borriana", "Bortigali",
                    "Borutta", "Bosa", "Boschi Sant'Anna", "Bosco Marengo", "Boscoreale",
                    "Bosentino", "Bosio", "Bosnasco", "Bossolasco", "Botrugno", "Botticino",
                    "Bova", "Bovalino", "Boves", "Boville Ernica", "Bovisio-Masciago",
                    "Bovolone", "Bozzolo", "Bracca", "Bracigliano", "Brallo di Pregola",
                    "Brandico", "Branzi", "Brebbia", "Bregano", "Bregnano", "Breia",
                    "Brembate di Sopra", "Brembio", "Brendola", "Brennero", "Brenta",
                    "Brentonico", "Brescello", "Bresimo", "Bressanone", "Bresso", "Brezzo di Bedero",
                    "Briatico", "Brienno", "Briga Alta", "Brignano Gera d'Adda", "Brindisi",
                    "Brinzio", "Brione", "Briosco", "Brissago-Valtravaglia", "Brittoli",
                    "Broccostella", "Brognaturo", "Brondello", "Bronte", "Brossasco",
                    "Brovello-Carpugnino", "Brugherio", "Brugnato", "Bruino", "Brunate",
                    "Brunico", "Brusaporto", "Brusciano", "Brusnengo", "Bruzolo", "Bubbiano",
                    "Buccheri", "Bucciano", "Buccino", "Buddusò", "Budoni", "Buggerru", "Buglio in Monte",
                    "Buguggiate", "Bulciago", "Bultei", "Buonabitacolo", "Buonconvento", "Burago di Molgora",
                    "Burgio", "Buriasco", "Buronzo", "Busalla", "Busano", "Buscate", "Buseto Palizzolo",
                    "Bussero", "Bussi sul Tirino", "Bussolengo", "Busto Arsizio", "Butera", "Buttapietra",
                    "Buttigliera d'Asti", "Ca' d'Andrea", "Cabiate", "Caccamo", "Cadegliano-Viconago",
                    "Cadeo", "Cadoneghe", "Cadrezzate", "Cafasse", "Cagli", "Caglio", "Cagnano Varano",
                    "Cagnò", "Caiazzo", "Caino", "Cairano", "Cairo Montenotte", "Calabritto",
                    "Calamandrana", "Calangianus", "Calasca-Castiglione", "Calascio", "Calatabiano",
                    "Calavino", "Calceranica al Lago", "Calciano", "Calcinate", "Calcio",
                    "Caldaro sulla Strada del Vino", "Calderara di Reno", "Caldiero", "Caldonazzo",
                    "Calenzano", "Calice al Cornoviglio", "Calimera", "Calizzano", "Calliano",
                    "Calolziocorte", "Calosso", "Caltabellotta", "Caltanissetta", "Caltignaga",
                    "Caltrano", "Caluso", "Calvanico", "Calvello", "Calvenzano", "Calvi",
                    "Calvi Risorta", "Calvignasco", "Calvizzano", "Camaiore", "Camandona",
                    "Cambiago", "Cambiasca", "Camerana", "Camerano Casasco", "Camerata Nuova",
                    "Cameri", "Camerota", "Caminata", "Camino", "Camisano", "Cammarata",
                    "Camogli", "Campagna Lupia", "Campagnatico", "Campagnola Emilia", "Camparada",
                    "Campello sul Clitunno", "Campi Bisenzio", "Campiglia Cervo", "Campiglia Marittima",
                    "Campione d'Italia", "Campli", "Campo di Giove", "Campo Ligure",
                    "Campo San Martino", "Campobasso", "Campobello di Mazara", "Campodarsego",
                    "Campodimele", "Campodolcino", "Campofelice di Fitalia", "Campofilone",
                    "Campoformido", "Campogalliano", "Campoli Appennino", "Campolieto",
                    "Campolongo sul Brenta", "Campomaggiore", "Campomorone", "Campora",
                    "Camporgiano", "Camporotondo di Fiastrone", "Camposampiero", "Camposanto",
                    "Campotosto", "Canal San Bovo", "Canale d'Agordo", "Canaro", "Cancellara",
                    "Canda", "Candelo", "Candia Lomellina", "Candida", "Candiolo", "Canelli",
                    "Caneva", "Canicattì", "Canino", "Canistro", "Cannalonga", "Cannero Riviera",
                    "Canneto sull'Oglio", "Cannole", "Canonica d'Adda", "Canosa Sannita", "Canossa",
                    "Cantagallo", "Cantalupa", "Cantalupo Ligure", "Cantarana", "Canterano", "Cantoira",
                    "Canzano", "Caorle", "Capaccio", "Capalbio", "Capannori", "Capergnanica",
                    "Capiago Intimiano", "Capistrello", "Capizzi", "Capo di Ponte", "Capodimonte",
                    "Capoliveri", "Caponago", "Caposele", "Capovalle", "Cappella Cantone",
                    "Cappella Maggiore", "Capracotta", "Capraia Isola", "Capranica",
                    "Caprarica di Lecce", "Caprauna", "Caprezzo", "Capri Leone", "Capriano del Colle",
                    "Capriate San Gervasio", "Caprie", "Capriglio", "Caprino Bergamasco",
                    "Capriolo", "Capua", "Caraffa del Bianco", "Caraglio", "Caramanico Terme",
                    "Carapelle", "Carasco", "Carate Brianza", "Caravaggio", "Caravino",
                    "Carbognano", "Carbonara di Nola", "Carbonara Scrivia", "Carbone", "Carbonia",
                    "Carceri", "Cardano al Campo", "Cardedu", "Cardinale", "Careggine", "Carenno",
                    "Careri", "Caresanablot", "Carfizzi", "Cariati", "Carignano", "Carinaro",
                    "Carinola", "Carisolo", "Carlazzo", "Carlino", "Carlopoli", "Carmiano",
                    "Carmignano di Brenta", "Carnate", "Carolei", "Caronia", "Caronno Varesino",
                    "Carovigno", "Carpaneto Piacentino", "Carpasio", "Carpenedolo", "Carpi",
                    "Carpignano Salentino", "Carpineti", "Carpineto Romano", "Carpino",
                    "Carrara", "Carrega Ligure", "Carrodano", "Carrù", "Cartigliano",
                    "Cartoceto", "Cartura", "Carugo", "Carvico", "Casabona", "Casacanditella",
                    "Casal Cermelli", "Casal Velino", "Casalattico", "Casalbordino",
                    "Casalborgone", "Casalbuttano ed Uniti", "Casalduni", "Casale Cremasco-Vidolasco",
                    "Casale Litta", "Casale Monferrato", "Casalecchio di Reno", "Casaleggio Novara",
                    "Casaletto Ceredano", "Casaletto Lodigiano", "Casaletto Vaprio", "Casalgrande",
                    "Casalincontrada", "Casalmaggiore", "Casalmorano", "Casalnoceto",
                    "Casalnuovo Monterotaro", "Casalpusterlengo", "Casalserugo",
                    "Casalvecchio di Puglia", "Casalvieri", "Casalzuigno", "Casamassima",
                    "Casandrino", "Casanova Lerrone", "Casape", "Casapinta", "Casapulla",
                    "Casargo", "Casarsa della Delizia", "Casasco", "Casatenovo", "Casavatore",
                    "Cascia", "Casciana Terme", "Cascinette d'Ivrea", "Caselette", "Caselle in Pittari",
                    "Caselle Lurani", "Caserta", "Casignana", "Casirate d'Adda", "Casnate con Bernate",
                    "Casola di Napoli", "Casola Valsenio", "Casole d'Elsa", "Casorate Primo",
                    "Casorezzo", "Casorzo", "Caspoggio", "Cassago Brianza", "Cassano d'Adda",
                    "Cassano Irpino", "Cassano Spinola", "Cassaro", "Cassina de' Pecchi",
                    "Cassina Valsassina", "Cassine", "Cassinetta di Lugagnano", "Cassola",
                    "Castagnaro", "Castagneto Po", "Castagnole delle Lanze", "Castagnole Piemonte",
                    "Castano Primo", "Castegnato", "Castel Baronia", "Castel Bolognese", "Castel Castagna",
                    "Castel Condino", "Castel d'Ario", "Castel del Giudice", "Castel del Piano",
                    "Castel di Casio", "Castel di Iudica", "Castel di Lucio", "Castel di Sasso",
                    "Castel Focognano", "Castel Gabbiano", "Castel Giorgio", "Castel Guelfo di Bologna",
                    "Castel Maggiore", "Castel Morrone", "Castel Rocchero", "Castel San Giorgio",
                    "Castel San Lorenzo", "Castel San Pietro Romano", "Castel San Vincenzo", "Castel Sant'Elia",
                    "Castel Vittorio", "Castelbaldo", "Castelbellino", "Castelbianco", "Castelbuono",
                    "Castelcovati", "Casteldaccia", "Casteldelfino", "Castelfidardo", "Castelfondo",
                    "Castelfranci", "Castelfranco di Sotto", "Castelfranco in Miscano", "Castelgomberto",
                    "Castelguglielmo", "Castellabate", "Castell'Alfero", "Castellammare del Golfo",
                    "Castellamonte", "Castellana Sicula", "Castellania", "Castellar", "Castellarano",
                    "Castell'Arquato", "Castell'Azzara", "Castellazzo Novarese",
                    "Castelleone di Suasa", "Castelletto Cervo", "Castelletto di Branduzzo",
                    "Castelletto Merli", "Castelletto Monferrato", "Castelletto Stura",
                    "Castelli", "Castellina in Chianti", "Castellinaldo", "Castellino Tanaro",
                    "Castello Cabiaglio", "Castello d'Argile", "Castello dell'Acqua",
                    "Castello di Brianza", "Castello di Godego", "Castello Tesino", "Castellucchio",
                    "Castelluccio Inferiore", "Castelluccio Valmaggiore", "Castelmagno", "Castelmassa",
                    "Castelmezzano", "Castelnovetto", "Castelnovo del Friuli", "Castelnovo ne' Monti",
                    "Castelnuovo Belbo", "Castelnuovo Bocca d'Adda", "Castelnuovo Bozzente",
                    "Castelnuovo Cilento", "Castelnuovo della Daunia", "Castelnuovo di Conza",
                    "Castelnuovo di Garfagnana", "Castelnuovo di Val di Cecina", "Castelnuovo Magra",
                    "Castelnuovo Parano", "Castelnuovo Scrivia", "Castelpetroso", "Castelplanio",
                    "Castelraimondo", "Castelsantangelo sul Nera", "Castelsardo", "Castelsilano",
                    "Casteltermini", "Castelvecchio Calvisio", "Castelvecchio Subequo", "Castelverde",
                    "Castelvetere in Val Fortore", "Castelvetrano", "Castelvetro Piacentino",
                    "Castenaso", "Castiadas", "Castiglion Fiorentino", "Castiglione Chiavarese",
                    "Castiglione d'Adda", "Castiglione del Genovesi", "Castiglione della Pescaia",
                    "Castiglione di Garfagnana", "Castiglione d'Intelvi", "Castiglione Falletto",
                    "Castiglione Messer Marino", "Castiglione Olona", "Castiglione Torinese",
                    "Castilenti", "Castione Andevenno", "Castions di Strada", "Casto", "Castrezzato",
                    "Castrignano de' Greci", "Castro", "Castro dei Volsci", "Castrocielo", "Castrolibero",
                    "Castronovo di Sicilia", "Castropignano", "Castroregio", "Catania", "Catenanuova",
                    "Cattolica", "Caulonia", "Cava de' Tirreni", "Cavacurta", "Cavaglietto",
                    "Cavaglio-Spoccia", "Cavaion Veronese", "Cavallasca", "Cavallermaggiore",
                    "Cavallino-Treporti", "Cavareno", "Cavaria con Premezzo", "Cavaso del Tomba",
                    "Cavatore", "Cave", "Cavedine", "Cavenago di Brianza", "Cavezzo", "Cavour",
                    "Cavriana", "Cazzago Brabbia", "Cazzano di Tramigna", "Ceccano", "Cecina",
                    "Cedrasco", "Cefalù", "Ceglie Messapica", "Celenza sul Trigno", "Celico",
                    "Cella Monte", "Cellara", "Cellatica", "Celle di Macra", "Celle Enomondo",
                    "Celleno", "Cellino Attanasio", "Cellio", "Cembra", "Cenate Sopra",
                    "Cencenighe Agordino", "Ceneselli", "Centa San Nicolò", "Cento", "Centrache",
                    "Cepagatti", "Ceppo Morelli", "Cerami", "Cerano", "Ceranova", "Cercemaggiore",
                    "Cercepiccola", "Cerchio", "Cercivento", "Cerda", "Ceregnano", "Ceres",
                    "Cereseto", "Ceresole Reale", "Ceretto Lomellina", "Ceriale", "Ceriano Laghetto",
                    "Cerignola", "Cermenate", "Cermignano", "Cernusco Lombardone", "Cerreto Castello",
                    "Cerreto d'Esi", "Cerreto Grue", "Cerreto Laziale", "Cerretto Langhe", "Cerrione",
                    "Cerro al Volturno", "Cerro Tanaro", "Cersosimo", "Certosa di Pavia",
                    "Cervara di Roma", "Cervaro", "Cervatto", "Cervere", "Cerveteri", "Cervicati",
                    "Cervignano del Friuli", "Cervino", "Cerzeto", "Cesana Brianza", "Cesano Boscone",
                    "Cesara", "Cesate", "Cesenatico", "Cesio", "Cessalto", "Cessapalombo", "Cetara",
                    "Cetona", "Ceva", "Challand-Saint-Anselme", "Chambave", "Champdepraz", "Charvensod",
                    "Cherasco", "Chialamberto", "Chianche", "Chianni", "Chiaramonte Gulfi", "Chiarano",
                    "Chiaravalle Centrale", "Chiaromonte", "Chiavari", "Chiaverano", "Chieri",
                    "Chiesa in Valmalenco", "Chiesina Uzzanese", "Chieuti",
                    "Chignolo d'Isola", "Chioggia", "Chions", "Chitignano",
                    "Chiuppano", "Chiusa", "Chiusa di San Michele", "Chiusaforte",
                    "Chiusano d'Asti", "Chiusavecchia", "Chiusi", "Chivasso",
                    "Cianciana", "Cicagna", "Cicciano", "Ciciliano", "Ciconio",
                    "Cigliè", "Cigole", "Cimadolmo", "Cimego", "Ciminna", "Cimolais",
                    "Cinaglio", "Cingia de' Botti", "Cinigiano", "Cinisi", "Cinquefrondi",
                    "Cinte Tesino", "Cinto Euganeo", "Ciorlano", "Circello", "Cirigliano",
                    "Cirò", "Cis", "Cisano sul Neva", "Cislago", "Cismon del Grappa",
                    "Cissone", "Cisterna di Latina", "Citerna", "Città di Castello", "Cittadella",
                    "Cittanova", "Cittiglio", "Civenna", "Civezzano", "Cividale del Friuli",
                    "Cividate Camuno", "Civita Castellana", "Civitacampomarano",
                    "Civitanova del Sannio", "Civitaquana", "Civitella Alfedena",
                    "Civitella d'Agliano", "Civitella di Romagna",
                    "Civitella Messer Raimondo", "Civitella Roveto", "Civo", "Claut", "Clavesana",
                    "Cles", "Clivio", "Clusone", "Coazze", "Coccaglio", "Cocquio-Trevisago",
                    "Codevigo", "Codigoro", "Codogno", "Codrongianos", "Cogliate", "Cogoleto",
                    "Cogorno", "Colbordolo", "Colfelice", "Colico", "Collalto Sabino", "Collazzone",
                    "Colle d'Anchise", "Colle di Val d'Elsa", "Colle Sannita", "Colle Umberto",
                    "Collecchio", "Colledara", "Colledimezzo", "Collegiove", "Collelongo",
                    "Collepasso", "Colleretto Castelnuovo", "Collesalvetti", "Colletorto",
                    "Colli a Volturno", "Colli sul Velino", "Collinas", "Collobiano", "Colmurano",
                    "Cologna Veneta", "Cologno al Serio", "Colognola ai Colli", "Colonnella",
                    "Colorina", "Colosimi", "Colzate", "Comacchio", "Comano Terme", "Comeglians",
                    "Comerio", "Comignago", "Comitini", "Commessaggio", "Como", "Comun Nuovo",
                    "Cona", "Conca dei Marini", "Concamarise", "Concesio", "Concordia Sagittaria",
                    "Concorezzo", "Condofuri", "Condrò", "Confienza", "Conflenti", "Conselice",
                    "Contessa Entellina", "Contrada", "Controne", "Conversano", "Conzano",
                    "Copiano", "Corana", "Corbara", "Corbola", "Corciano", "Cordignano", "Coredo",
                    "Coreglia Ligure", "Corfinio", "Coriano", "Corigliano d'Otranto", "Corio",
                    "Corleto Monforte", "Cormano", "Corna Imagna", "Cornale", "Cornate d'Adda",
                    "Cornedo Vicentino", "Corneliano d'Alba", "Corno di Rosazzo", "Cornovecchio",
                    "Correggio", "Correzzola", "Corridonia", "Corsano", "Corsione",
                    "Cortale", "Cortanze", "Corte Brugnatella", "Corte de' Frati",
                    "Corte Palasio", "Cortemilia", "Cortenova", "Corteolona", "Cortina d'Ampezzo",
                    "Cortino", "Corvara", "Corvino San Quirico", "Coseano", "Cosio d'Arroscia",
                    "Cosoleto", "Cossano Canavese", "Cosseria", "Cossogno", "Cossombrato",
                    "Costa di Mezzate", "Costa Masnaga", "Costa Valle Imagna", "Costa Volpino",
                    "Costacciaro", "Costarainera", "Costigliole d'Asti", "Cotignola",
                    "Cottanello", "Covo", "Craco", "Cravagliana", "Craveggia", "Crecchio",
                    "Credera Rubbiano", "Cremella", "Cremeno", "Cremolino", "Cremosano",
                    "Crespadoro", "Crespellano", "Crespina", "Cressa", "Crevalcore",
                    "Crispano", "Crissolo", "Crocetta del Montello", "Crognaleto",
                    "Cropani", "Crosia", "Crotone", "Crova", "Crucoli", "Cuccaro Monferrato",
                    "Cucciago", "Cuggiono", "Cuglieri", "Cumiana", "Cunardo", "Cunevo",
                    "Cuorgnè", "Cupra Marittima", "Cura Carpignano", "Cureggio", "Curinga",
                    "Curno", "Cursi", "Curtarolo", "Curti", "Cusano Milanino", "Cusino",
                    "Custonaci", "Cutro", "Cuveglio", "Daiano", "Dalmine", "Danta di Cadore",
                    "Darè", "Dasà", "Daverio", "Dazio", "Decimoputzu", "Dego", "Delebio",
                    "Delianuova", "Dello", "Denice", "Dernice", "Deruta", "Desana", "Desio",
                    "Diamante", "Diano Castello", "Diano Marina", "Dicomano", "Dimaro",
                    "Dipignano", "Divignano", "Dobbiaco", "Dogliani", "Dogna", "Dolceacqua",
                    "Dolegna del Collio", "Dolo", "Domanico", "Domegge di Cadore",
                    "Domodossola", "Domusnovas", "Donato", "Donnas", "Dorgali", "Dormelletto",
                    "Dorsino", "Dosolo", "Dosso del Liro", "Dovadola", "Dozza", "Drapia",
                    "Drenchia", "Drezzo", "Dro", "Druento", "Dualchi", "Due Carrare",
                    "Dugenta", "Dumenza", "Durazzano", "Dusino San Michele", "Edolo",
                    "Elice", "Ello", "Elva", "Empoli", "Enego", "Enna", "Entratico",
                    "Episcopia", "Erba", "Erbezzo", "Erchie", "Erice", "Erto e Casso",
                    "Erve", "Escalaplano", "Esine", "Esperia", "Este", "Etroubles",
                    "Exilles", "Fabbriche di Vallico", "Fabriano", "Fabrizia", "Faedis",
                    "Faedo Valtellino", "Faeto", "Faggeto Lario", "Fagnano Alto",
                    "Fagnano Olona", "Faicchio", "Falciano del Massico", "Falconara Marittima",
                    "Faleria", "Falerone", "Falmenta", "Falvaterra", "Fanano", "Fano",
                    "Fara Filiorum Petri", "Fara in Sabina", "Fara Olivana con Sola",
                    "Fara Vicentino", "Farigliano", "Farini", "Farra d'Alpago", "Farra d'Isonzo",
                    "Fascia", "Faule", "Favara", "Favignana", "Feisoglio", "Felino", "Felizzano",
                    "Feltre", "Fenestrelle", "Ferentillo", "Ferla", "Fermo", "Feroleto Antico",
                    "Ferrandina", "Ferrara di Monte Baldo", "Ferrera di Varese", "Ferrere",
                    "Ferruzzano", "Fiano", "Fiastra", "Ficarazzi", "Ficarra", "Fidenza",
                    "Fiera di Primiero", "Fiesco", "Fiesse", "Fiesso Umbertiano",
                    "Figline Valdarno", "Filacciano", "Filago", "Filattiera", "Filetto",
                    "Filighera", "Filogaso", "Finale Emilia", "Fino del Monte", "Fiorano al Serio",
                    "Fiorano Modenese", "Fiorenzuola d'Arda", "Firenzuola", "Fisciano", "Fiumalbo",
                    "Fiume Veneto", "Fiumefreddo Bruzio", "Fiumicello", "Fiuminata", "Flaibano",
                    "Flero", "Floridia", "Flumeri", "Flussio", "Foggia", "Fogliano Redipuglia",
                    "Foiano della Chiana", "Folgaria", "Foligno", "Follo", "Fombio", "Fondi",
                    "Fonni", "Fontana Liri", "Fontanarosa", "Fontanella", "Fontanelle",
                    "Fontanetto Po", "Fontanile", "Fonte",
                    "Fontecchio", "Fontegreca", "Fontevivo", "Foppolo", "Force", "Forcola", "Forenza",
                    "Forgaria nel Friuli", "Forio", "Forlì del Sannio", "Formazza", "Formia",
                    "Formigara", "Formigliana", "Fornace", "Forni Avoltri", "Forni di Sotto",
                    "Forno di Zoldo", "Fornovo San Giovanni", "Fortezza", "Forza d'Agrò",
                    "Fosdinovo", "Fossacesia", "Fossalta di Portogruaro", "Fossano",
                    "Fossato Serralta", "Fossombrone", "Frabosa Soprana", "Fraconalto",
                    "Fragneto L'Abate", "Fraine", "Francavilla al Mare", "Francavilla Bisio",
                    "Francavilla di Sicilia", "Francavilla in Sinni", "Francica", "Francolise",
                    "Frascarolo", "Frascineto", "Frassinelle Polesine", "Frassineto Po", "Frassino",
                    "Frasso Sabino", "Fratta Polesine", "Frattamaggiore", "Fratte Rosa", "Fregona",
                    "Fresonara", "Frignano", "Frisa", "Front", "Frontone", "Frosolone", "Frugarolo",
                    "Fucecchio", "Fumane", "Funes", "Furci Siculo", "Furore", "Fuscaldo", "Fusine",
                    "Gabbioneta-Binanuova", "Gabicce Mare", "Gadesco-Pieve Delmona", "Gaeta", "Gaggiano",
                    "Gaglianico", "Gagliano Castelferrato", "Gagliato", "Gaiarine", "Gaiola", "Gairo",
                    "Galati Mamertino", "Galatone", "Galbiate", "Galgagnano", "Gallese",
                    "Galliate Lombardo", "Gallicano", "Gallicchio", "Galliera Veneta", "Gallio",
                    "Gallo Matese", "Galluccio", "Galzignano Terme", "Gambara", "Gambasca",
                    "Gambatesa", "Gamberale", "Gambolò", "Gandellino", "Gandosso", "Garaguso",
                    "Garbagna Novarese", "Garbagnate Monastero", "Gardone Riviera", "Garessio",
                    "Gargazzone", "Garlasco", "Garlenda", "Garzeno", "Gasperina", "Gattatico",
                    "Gattico", "Gavardo", "Gavello", "Gavi", "Gavirate", "Gavorrano", "Gazzada Schianno",
                    "Gazzo", "Gazzola", "Gela", "Gemona del Friuli", "Genazzano", "Genivolta",
                    "Genoni", "Genuri", "Genzano di Roma", "Gera Lario", "Geraci Siculo",
                    "Gerenzago", "Gergei", "Germagno", "Gerocarne", "Gerosa", "Gesico",
                    "Gessopalena", "Gesualdo", "Ghemme", "Ghilarza", "Ghislarengo", "Giaglione",
                    "Giano dell'Umbria", "Giardinello", "Giarole", "Giarre", "Giaveno",
                    "Giba", "Gifflenga", "Giffoni Sei Casali", "Gignese", "Gildone",
                    "Ginestra", "Ginosa", "Gioia dei Marsi", "Gioia Sannitica", "Gioiosa Ionica",
                    "Giove", "Giovo", "Girifalco", "Gissi", "Giugliano in Campania",
                    "Giuliano di Roma", "Giulianova", "Giungano", "Giussago", "Giustenice",
                    "Giusvalla", "Gizzeria", "Godega di Sant'Urbano", "Godrano", "Golasecca",
                    "Golfo Aranci", "Gonars", "Gonnesa", "Gonnosfanadiga", "Gonnostramatza",
                    "Gordona", "Gorgo al Monticano", "Gorgonzola", "Gorizia", "Gorla Minore",
                    "Gorle", "Gorno", "Gorreto", "Gosaldo", "Gottasecca", "Govone",
                    "Gradara", "Grado", "Graffignana", "Graglia", "Gragnano Trebbiense",
                    "Grana", "Granarolo dell'Emilia", "Grandate", "Graniti", "Grantola",
                    "Granze", "Grassobbio", "Grauno", "Gravellona Lomellina", "Gravere",
                    "Gravina in Puglia", "Grazzano Badoglio", "Greci", "Gremiasco",
                    "Gressoney-la-Trinitè", "Greve in Chianti", "Grezzana", "Gricignano di Aversa",
                    "Grigno", "Grimaldi", "Grisignano di Zocco", "Grizzana Morandi", "Gromo",
                    "Grone", "Gropello Cairoli", "Groscavallo", "Grosotto", "Grosso",
                    "Grottaglie", "Grottammare", "Grotte", "Grotteria", "Grottolella",
                    "Grugliasco", "Grumello del Monte", "Grumes", "Grumo Nevano", "Guagnano",
                    "Gualdo Cattaneo", "Gualtieri", "Guamaggiore", "Guarcino",
                    "Guardabosone", "Guardavalle", "Guardia Lombardi", "Guardia Piemontese",
                    "Guardiagrele", "Guardiaregia", "Guarene", "Guastalla", "Gubbio",
                    "Guglionesi", "Guidonia Montecelio", "Guilmi", "Guspini", "Gussola",
                    "Idro", "Igliano", "Illasi", "Imbersago", "Imola", "Impruneta",
                    "Incisa in Val d'Arno", "Incudine", "Ingria", "Introbio", "Introdacqua",
                    "Inverigo", "Inverso Pinasca", "Invorio", "Ionadi", "Irma", "Isasca",
                    "Ischia", "Ischitella", "Isera", "Isili", "Isola d'Asti", "Isola del Giglio",
                    "Isola del Liri", "Isola della Scala", "Isola di Capo Rizzuto",
                    "Isola Dovarese", "Isola Sant'Antonio", "Isolabella", "Isole Tremiti",
                    "Ispani", "Ispra", "Issime", "Issogne", "Itala", "Ittireddu",
                    "Ivano-Fracena", "Izano", "Jelsi", "Jerago con Orago", "Jesi",
                    "Jolanda di Savoia", "Joppolo Giancaxio", "La Cassa", "La Maddalena",
                    "La Morra", "La Spezia", "La Valle", "Labico", "Lacchiarella",
                    "Lacedonia", "Laconi", "Laerru", "Laghi", "Lagnasco", "Lagonegro",
                    "Lagundo", "Lainate", "Laino Borgo", "Laion", "Lajatico", "Lama dei Peligni",
                    "Lambrugo", "Lamon", "Lamporecchio", "Lana", "Landiona", "Langhirano",
                    "Lanusei", "Lanzada", "Lanzo Torinese", "Lapio", "L'Aquila", "Lardaro",
                    "Lari", "Larino", "Lasa", "Lasino", "Lastebasse", "Latera", "Laterza",
                    "Latina", "Latronico", "Lauco", "Laureana di Borrello", "Laurenzana",
                    "Lauriano", "Laurito", "Lavagna", "Lavarone", "Lavena Ponte Tresa",
                    "Lavenone", "Lavis", "Lazzate", "Lecce nei Marsi", "Ledro", "Leggiuno",
                    "Legnano", "Lei", "Leivi", "Lendinara", "Lenna", "Leno", "Lenta",
                    "Lentella", "Lentini", "Leonforte", "Lequile", "Lequio Tanaro",
                    "Lerici", "Lesa", "Lesignano de' Bagni", "Lesmo", "Lessona",
                    "Letino", "Lettere", "Lettopalena", "Levate", "Levice",
                    "Levone", "Liberi", "Licata", "Licenza", "Lierna",
                    "Lignano Sabbiadoro", "Ligosullo", "Limana", "Limbadi",
                    "Limena", "Limina", "Limone sul Garda", "Linarolo", "Lioni",
                    "Lipomo", "Liscate", "Lisciano Niccone", "Lisio", "Liveri",
                    "Livinallongo del Col di Lana", "Livo", "Livorno Ferraris",
                    "Lizzanello", "Lizzano in Belvedere", "Loazzolo", "Locate di Triulzi",
                    "Locatello", "Locorotondo", "Loculi", "Lodi", "Lodine", "Lograto",
                    "Loiri Porto San Paolo", "Lomazzo", "Lombriasco", "Lona-Lases",
                    "Lonate Pozzolo", "Londa", "Longare", "Longhena", "Longiano",
                    "Longobucco", "Longone Sabino", "Loranzè", "Loreglia", "Lorenzana",
                    "Loreto", "Loria", "Loro Piceno", "Losine", "Lovere", "Lozio",
                    "Lozzo Atestino", "Lozzolo", "Lubriano", "Lucca Sicula", "Lucignano",
                    "Lucito", "Lucoli", "Lugnacco", "Lugo", "Luino", "Lula", "Lumezzane",
                    "Lunano", "Lungro", "Luogosanto", "Lurago d'Erba", "Lurano", "Lurate Caccivio",
                    "Luserna", "Lusernetta", "Lusia", "Lusigliè", "Lustra", "Luzzana", "Luzzi",
                    "Maccastorna", "Macchia Valfortore", "Macello", "Macerata Campania", "Macherio",
                    "Macomer", "Macugnaga", "Madesimo", "Madone", "Maenza", "Magasa", "Maggiora",
                    "Magione", "Magliano Alfieri", "Magliano de' Marsi", "Magliano in Toscana",
                    "Magliano Sabina", "Maglie", "Maglione", "Magnago", "Magnano in Riviera",
                    "Magrè sulla Strada del Vino", "Maida", "Maierato", "Maiolo", "Mairago",
                    "Maissana", "Malagnino", "Malborghetto Valbruna", "Malè", "Maleo", "Maletto",
                    "Malgesso", "Malito", "Malles Venosta", "Malo", "Malosco", "Malvagna",
                    "Malvito", "Mamoiada", "Mandanici", "Mandatoriccio", "Mandello del Lario",
                    "Manduria", "Manerbio", "Mango", "Maniace", "Manocalzati", "Mansuè",
                    "Mantello", "Manzano", "Mapello", "Maracalagonis", "Marano di Napoli",
                    "Marano Equo", "Marano Marchesato", "Marano sul Panaro", "Marano Vicentino",
                    "Maratea", "Marcaria", "Marcellina", "Marcetelli", "Marchirolo",
                    "Marciana Marina", "Marciano della Chiana", "Marcon", "Marene",
                    "Marentino", "Margarita", "Margno", "Mariano Comense", "Marianopoli",
                    "Marigliano", "Marineo", "Marlengo", "Marmentino", "Marmora",
                    "Marone", "Marostica", "Marrubiu", "Marsala", "Marsico Nuovo",
                    "Marta", "Martellago", "Martignacco", "Martignano", "Martinengo",
                    "Martinsicuro", "Martirano Lombardo", "Martone", "Maruggio",
                    "Marzano", "Marzano di Nola", "Marzio", "Masate", "Mascalucia",
                    "Masciago Primo", "Masera", "Maserada sul Piave", "Masi Torello",
                    "Maslianico", "Masone", "Massa d'Albe", "Massa e Cozzile", "Massa Fiscaglia",
                    "Massa Lubrense", "Massa Martana", "Massalengo", "Massarosa", "Massello",
                    "Massignano", "Massimino", "Massiola", "Matelica", "Mathi", "Matrice",
                    "Mattinata", "Mazzano", "Mazzarino", "Mazzarrone", "Mazzin", "Meana di Susa",
                    "Meda", "Medea", "Medicina", "Medolago", "Medolla", "Meduno",
                    "Megliadino San Vitale", "Mel", "Melazzo", "Mele", "Melendugno",
                    "Melfi", "Melicucco", "Melissa", "Melito di Napoli", "Melito Irpino",
                    "Melle", "Melpignano", "Melzo", "Menarola", "Mendatica", "Menfi",
                    "Meolo", "Merano", "Mercallo", "Mercatino Conca", "Mercato Saraceno",
                    "Mercogliano", "Mergo", "Merì", "Merlino", "Mesagne", "Mesenzana",
                    "Mesola", "Messina", "Meta", "Mezzago", "Mezzana Bigli",
                    "Mezzana Rabattone", "Mezzanego", "Mezzanino", "Mezzegra", "Mezzocorona",
                    "Mezzoldo", "Mezzomerico", "Miane", "Miazzina", "Miggiano", "Migliarino",
                    "Miglierina", "Mignanego", "Milano", "Milena", "Milis",
                    "Militello Rosmarino", "Milo", "Mineo", "Minerbio", "Minervino Murge",
                    "Minturno", "Mioglia", "Mirabella Eclano", "Mirabello",
                    "Mirabello Sannitico", "Miranda", "Mirano", "Misano Adriatico", "Misilmeri",
                    "Missaglia", "Misterbianco", "Moasca", "Modena", "Modigliana", "Modugno", "Moggio", "Moglia", "Mogliano Veneto", "Mogoro", "Moimacco", "Moio de' Calvi", "Moiola", "Molare", "Molfetta", "Molinara", "Molini di Triora", "Molise", "Mollia", "Molteno", "Molvena", "Mombaldone", "Mombaroccio", "Mombasiglio", "Mombello Monferrato", "Momo", "Mompeo", "Monacilioni", "Monasterace", "Monastero di Lanzo", "Monasterolo Casotto", "Monasterolo di Savigliano", "Monastir", "Moncalvo", "Moncestino", "Monchio delle Corti", "Moncrivello", "Mondaino", "Mondolfo", "Mondragone", "Monesiglio", "Monforte d'Alba", "Monfumo", "Monghidoro", "Mongiardino Ligure", "Mongrando", "Monguelfo-Tesido", "Moniga del Garda", "Monno", "Monreale", "Monsampietro Morico", "Monsano", "Monserrato", "Montà", "Montacuto", "Montagano", "Montagna in Valtellina", "Montagnareale", "Montaguto", "Montalbano Elicona", "Montalcino", "Montaldo Bormida", "Montaldo Roero", "Montaldo Torinese", "Montalenghe", "Montalto delle Marche", "Montalto Dora", "Montalto Pavese", "Montanaro", "Montanera", "Montano Lucino", "Montaquila", "Montauro", "Monte Argentario", "Monte Cavallo", "Monte Colombo", "Monte Cremasco", "Monte di Procida", "Monte Grimano Terme", "Monte Marenzo", "Monte Porzio Catone", "Monte Roberto", "Monte San Biagio", "Monte San Giovanni Campano", "Monte San Giusto", "Monte San Pietrangeli", "Monte San Savino", "Monte Santa Maria Tiberina", "Monte Urano", "Monte Vidon Corrado", "Montebello di Bertona", "Montebello sul Sangro", "Montebelluna", "Montebuono", "Montecalvo Irpino", "Montecarlo", "Montecassiano", "Montecastrilli", "Montecatini-Terme", "Montecchio", "Montecchio Maggiore", "Montechiaro d'Acqui", "Montechiarugolo", "Montecilfone", "Montecorice", "Montecorvino Rovella", "Montecrestese", "Montedinove", "Montefalcione", "Montefalcone Appennino", "Montefalcone nel Sannio", "Montefelcino", "Montefiascone", "Montefiore Conca", "Montefiorino", "Monteforte Cilento", "Monteforte Irpino", "Montefranco", "Montefusco", "Montegalda", "Montegallo", "Montegiordano", "Montegranaro", "Montegrino Valtravaglia", "Montegrosso Pian Latte", "Monteiasi", "Montelanico", "Monteleone di Fermo", "Monteleone di Spoleto", "Monteleone Rocca Doria", "Montelepre", "Montella", "Montelongo", "Montelupo Albese", "Montelupone", "Montemaggiore Belsito", "Montemale di Cuneo", "Montemarciano", "Montemesola", "Montemignaio", "Montemilone", "Montemonaco", "Montemurro", "Montenero di Bisaccia", "Montenero Val Cocchiara", "Monteodorisio", "Monteparano", "Montepulciano", "Monterchi", "Montereale Valcellina", "Monteriggioni", "Monteroni d'Arbia", "Monterosi", "Monterosso Almo", "Monterosso Grana", "Monterotondo Marittimo", "Montesano Salentino", "Montesarchio", "Montescano", "Montescudaio", "Montese", "Montesilvano", "Monteu da Po", "Montevago", "Montevecchia", "Monteverde", "Monteviale", "Monti", "Monticelli Brusati", "Monticelli Pavese", "Monticello Conte Otto", "Montichiari", "Montieri", "Montignoso", "Montjovet", "Montoggio", "Montopoli di Sabina", "Montorfano", "Montorio nei Frentani", "Montoro Inferiore", "Montorso Vicentino", "Montresta", "Monvalle", "Monzambano", "Morano Calabro", "Moransengo", "Morazzone", "Morbello", "Morciano di Romagna", "Mordano", "Mores", "Moretta", "Morgano", "Morgongiori", "Moriago della Battaglia", "Morigerati", "Morino", "Morlupo", "Mornago", "Mornico al Serio", "Morolo", "Morra De Sanctis", "Morro d'Oro", "Morrone del Sannio", "Morsano al Tagliamento", "Mortara", "Morterone", "Moscazzano", "Mosciano Sant'Angelo", "Moso in Passiria", "Mossano", "Motta Baluffi", "Motta d'Affermo", "Motta di Livenza", "Motta San Giovanni", "Motta Sant'Anastasia", "Mottafollone", "Motteggiana", "Mozzagrogna", "Mozzate", "Mozzo", "Muggia", "Mugnano del Cardinale", "Mulazzano", "Mura", "Murazzano", "Murialdo", "Murlo", "Muro Lucano", "Muscoline", "Musile di Piave", "Mussolente", "Muzzana del Turgnano", "Nago-Torbole", "Nanno", "Napoli", "Narcao", "Nardodipace", "Naro", "Nasino", "Naturno", "Nave San Rocco", "Naz-Sciaves", "Ne", "Negrar", "Neive", "Nemi", "Neoneli", "Nereto", "Nervesa della Battaglia", "Nespolo", "Netro", "Neviano", "Neviglie", "Nibbiano", "Nibionno", "Nicolosi", "Nicosia", "Niella Belbo", "Nimis", "Nissoria", "Nizza Monferrato", "Noasca", "Nocciano", "Nocera Superiore", "Nocera Umbra", "Noci", "Noepoli", "Nogaredo", "Nogarole Vicentino", "Nola", "Noli", "Nomi", "None", "Noragugume", "Norcia", "Nosate", "Noto", "Nova Milanese", "Nova Siri", "Novaledo", "Novara", "Novate Mezzola", "Nove", "Novellara", "Noventa di Piave", "Noventa Vicentina", "Novi Ligure", "Noviglio", "Nucetto", "Nughedu Santa Vittoria", "Nulvi", "Nuoro", "Nuragus", "Nuraminis", "Nurri", "Nusco", "Nuvolera", "Occhieppo Inferiore", "Occhiobello", "Ocre", "Odalengo Piccolo", "Odolo", "Offagna", "Offida", "Oggebbio", "Oggiono", "Ogliastro Cilento", "Olcenengo", "Oleggio", "Olevano di Lomellina", "Olevano sul Tusciano", "Olgiate Molgora", "Olginate", "Oliva Gessi", "Oliveri", "Oliveto Lario", "Olivetta San Michele", "Ollastra", "Ollomont", "Olmeneta", "Olmo Gentile", "Oltressenda Alta", "Olzai", "Omegna", "Onanì", "Oncino", "Onifai", "Ono San Pietro", "Onzo", "Opi", "Oppido Lucano", "Ora", "Oratino", "Orbetello", "Orciano Pisano", "Ordona", "Orgiano", "Oria", "Origgio", "Orio al Serio", "Orio Litta", "Oriolo Romano", "Ormea", "Ornago", "Ornica", "Orotelli", "Orroli", "Orsara Bormida", "Orsenigo", "Orsomarso", "Orta Nova", "Ortacesus", "Ortelle", "Ortignano Raggiolo", "Ortona", "Ortonovo", "Ortucchio", "Orune", "Orvinio", "Orzivecchi", "Osasio", "Osidda", "Osilo", "Osini", "Osio Sotto", "Osnago", "Ospedaletti", "Ospedaletto d'Alpinolo", "Ospedaletto Lodigiano", "Ospitaletto", "Ossana", "Ossimo", "Ossuccio", "Ostellato", "Ostiglia", "Ostra Vetere", "Otranto", "Ottana", "Ottaviano", "Ottobiano", "Oulx", "Ovaro", "Ovindoli", "Oyace", "Ozieri", "Ozzano Monferrato", "Pabillonis", "Paceco", "Pachino", "Padenghe sul Garda", "Paderna", "Paderno del Grappa", "Paderno Franciacorta", "Padova", "Padru", "Paduli", "Paese", "Paganico Sabino", "Pagliara", "Pagnacco", "Pagnona", "Pago Veiano", "Paitone", "Palagano", "Palagiano", "Palaia", "Palata", "Palazzago", "Palazzo Canavese", "Palazzo San Gervasio", "Palazzolo dello Stella", "Palazzolo Vercellese", "Palena", "Palermo", "Palestro", "Palizzi", "Pallanzeno", "Palma Campania", "Palmanova", "Palmas Arborea", "Palmiano", "Palo del Colle", "Palombaro", "Palosco", "Palù del Fersina", "Paluzza", "Pancalieri", "Panchià", "Panettieri", "Pannarano", "Pantelleria", "Paola", "Papasidero", "Parabiago", "Paratico", "Parè", "Parenti", "Pareto", "Parlasco", "Parodi Ligure", "Parolise", "Parrano", "Partanna", "Paruzzaro", "Pasian di Prato", "Paspardo", "Passignano sul Trasimeno", "Pastena", "Pastrengo", "Pasturo", "Paternò", "Paternopoli", "Pattada", "Patù", "Paularo", "Paulilatino", "Paupisi", "Pavia", "Pavone Canavese", "Pavullo nel Frignano", "Peccioli", "Pecetto di Valenza", "Pecorara", "Pedara", "Pedavena", "Pederobba", "Pedivigliano", "Peglio", "Pegognaga", "Peio", "Pella", "Pellezzano", "Pellizzano", "Penango", "Penna San Giovanni", "Pennabilli", "Pennapiedimonte", "Pentone", "Perarolo di Cadore", "Percile", "Perdaxius", "Perego", "Perfugas", "Pergine Valsugana", "Perinaldo", "Perledo", "Perlo", "Pernumia", "Perosa Argentina", "Perrero", "Pertengo", "Pertica Bassa", "Pertusio", "Pesaro", "Pescantina", "Pescarolo ed Uniti", "Pescate", "Peschici", "Peschiera del Garda", "Pescina", "Pescocostanzo", "Pescopagano", "Pescorocchiano", "Pescosolido", "Pessina Cremonese", "Petacciato", "Petina", "Petralia Sottana", "Petrella Tifernina", "Petriolo", "Petrizzi", "Petrosino", "Pettenasco", "Pettineo", "Pettorano sul Gizio", "Peveragno", "Pezzaze", "Piacenza", "Piadena", "Piaggine", "Pian di Sco", "Piana degli Albanesi", "Piancastagnaio", "Piandimeleto", "Pianella", "Pianello Val Tidone", "Pianezza", "Pianfei", "Pianiga", "Pianopoli", "Piansano", "Piario", "Piateda", "Piazza al Serchio", "Piazza Brembana", "Piazzola sul Brenta", "Picciano", "Picinisco", "Piea", "Piedimonte Etneo", "Piedimonte San Germano", "Piegaro", "Pieranica", "Pietra Ligure", "Pietrabbondante", "Pietracamela", "Pietracupa", "Pietraferrazzana", "Pietragalla", "Pietramelara", "Pietranico", "Pietrapertosa", "Pietraporzio", "Pietrarubbia", "Pietrastornina", "Pietrelcina", "Pieve Albignola", "Pieve del Cairo", "Pieve di Cadore", "Pieve di Coriano", "Pieve di Teco", "Pieve Emanuele", "Pieve Fosciana", "Pieve Porto Morone", "Pieve Santo Stefano", "Pieve Torina", "Pievebovigliana", "Piglio", "Pignataro Interamna", "Pignola", "Pigra", "Pimentel", "Pinarolo Po", "Pincara", "Pineto", "Pino sulla Sponda del Lago Maggiore", "Pinzano al Tagliamento", "Piobbico", "Piobesi Torinese", "Pioltello", "Piombino Dese", "Piossasco", "Piove di Sacco", "Piovera", "Piozzo", "Pisa", "Piscina", "Pisciotta", "Pisoniano", "Pistoia", "Pitigliano", "Piuro", "Pizzale", "Pizzo", "Pizzoli", "Pizzoni", "Plataci", "Platì", "Plesio", "Plodio", "Pocenia", "Podenzano", "Poggiardo", "Poggio a Caiano", "Poggio Bustone", "Poggio Imperiale", "Poggio Moiano", "Poggio Picenze", "Poggio Rusco", "Poggio San Marcello", "Poggio Sannita", "Poggiofiorito", "Poggioreale", "Poggiridenti", "Pognana Lario", "Pogno", "Pojana Maggiore", "Polcenigo", "Polesine Parmense", "Polia", "Polignano a Mare", "Polino", "Polizzi Generosa", "Pollein", "Pollenza", "Pollina", "Pollutri", "Polpenazze del Garda", "Polverigi", "Pomaretto", "Pomaro Monferrato", "Pombia", "Pomigliano d'Arco", "Pompeiana", "Pomponesco", "Poncarale", "Ponna", "Ponso", "Pontboset", "Ponte", "Ponte dell'Olio", "Ponte di Piave", "Ponte in Valtellina", "Ponte nelle Alpi", "Ponte Nossa", "Ponte San Pietro", "Pontecagnano Faiano", "Pontechianale", "Pontecurone", "Pontedera", "Pontelatone", "Pontenure", "Pontestura", "Pontey", "Ponti sul Mincio", "Pontinia", "Pontirolo Nuovo", "Pontremoli", "Ponza", "Ponzano Monferrato", "Ponzano Veneto", "Popoli", "Porano", "Porcia", "Porlezza", "Porpetto", "Portacomaro", "Porte", "Portico di Caserta", "Portigliola", "Porto Ceresio", "Porto Empedocle", "Porto Recanati", "Porto Sant'Elpidio", "Porto Torres", "Porto Viro", "Portocannone", "Portofino", "Portomaggiore", "Portoscuso", "Portula", "Posina", "Possagno", "Posta Fibreno", "Postalesio", "Postua", "Potenza Picena", "Povegliano", "Poviglio", "Pozza di Fassa", "Pozzaglio ed Uniti", "Pozzilli", "Pozzol Groppo", "Pozzoleone", "Pozzomaggiore", "Pozzuoli", "Pozzuolo Martesana", "Pradamano", "Pragelato", "Praiano", "Prali", "Pralungo", "Pramollo", "Prarostino", "Prascorsano", "Prata Camportaccio", "Prata di Pordenone", "Prata Sannita", "Pratiglione", "Prato allo Stelvio", "Prato Sesia", "Pratola Serra", "Pravisdomini", "Prazzo", "Preci", "Predazzo", "Predore", "Preganziol", "Prelà", "Premariacco", "Premia", "Premolo", "Preone", "Prepotto", "Preseglie", "Presezzo", "Pressana", "Pretoro", "Prezza", "Priero", "Prignano sulla Secchia", "Priocca", "Priolo Gargallo", "Prizzi", "Procida", "Proserpio", "Provaglio d'Iseo", "Proves", "Prunetto", "Puglianello", "Pulfero", "Pumenengo", "Pusiano", "Putignano", "Quadri", "Qualiano", "Quaregna", "Quarna Sopra", "Quarona", "Quart", "Quarto d'Altino", "Quartucciu", "Quattordio", "Quero", "Quincinetto", "Quingentole", "Quinto di Treviso", "Quinto Vicentino", "Quistello", "Rabbi", "Racalmuto", "Raccuja", "Radda in Chianti", "Radicofani", "Raffadali", "Ragogna", "Ragusa", "Ramacca", "Ramponio Verna", "Ranco", "Ranica", "Ranzo", "Rapallo", "Rapolano Terme", "Rapone", "Rasun Anterselva", "Ravanusa", "Ravascletto", "Ravenna", "Raviscanina", "Rea", "Reana del Rojale", "Recale", "Recco", "Recoaro Terme", "Redondesco", "Refrontolo", "Reggello", "Reggio Emilia", "Reino", "Remanzacco", "Renate", "Renon", "Rescaldina", "Resiutta", "Retorbido", "Revere", "Revine Lago", "Rezzago", "Rezzo", "Rhemes-Notre-Dame", "Rho", "Rialto", "Riardo", "Ribordone", "Ricaldone", "Riccione", "Ricengo", "Riese Pio X", "Rieti", "Rifreddo", "Rignano Garganico", "Rigolato", "Rimasco", "Rimini", "Rio Marina", "Rio Saliceto", "Riola Sardo", "Riolunato", "Rionero in Vulture", "Ripa Teatina", "Ripacandida", "Ripalta Arpina", "Ripalta Guerina", "Ripatransone", "Ripe San Ginesio", "Riposto", "Riva del Garda", "Riva Ligure", "Riva Valdobbia", "Rivalta Bormida", "Rivamonte Agordino", "Rivara", "Rivarolo del Re ed Uniti", "Rivarone", "Rive", "Rivello", "Rivignano", "Rivodutri", "Rivoli Veronese", "Rizziconi", "Roana", "Roascio", "Roatto", "Robbiate", "Robecchetto con Induno", "Robecco Pavese", "Robella", "Roburent", "Rocca Canterano", "Rocca d'Arazzo", "Rocca de' Baldi", "Rocca D'Evandro", "Rocca di Cambio", "Rocca di Mezzo", "Rocca di Papa", "Rocca Imperiale", "Rocca Pia", "Rocca Priora", "Rocca San Felice", "Rocca Santa Maria", "Rocca Sinibalda", "Roccabascerana", "Roccabianca", "Roccacasale", "Roccafiorita", "Roccaforte del Greco", "Roccaforte Mondovì", "Roccafranca", "Roccagloriosa", "Roccalbegna", "Roccamandolfi", "Roccamonfina", "Roccamorice", "Roccantica", "Roccapiemonte", "Roccaraso", "Roccascalegna", "Roccasecca dei Volsci", "Roccasparvera", "Roccastrada", "Roccaverano", "Roccavione", "Roccella Ionica", "Rocchetta a Volturno", "Rocchetta di Vara", "Rocchetta Ligure", "Rocchetta Palafea", "Rocchetta Tanaro", "Roddi", "Rodello", "Rodengo Saiano", "Rodi Garganico", "Rodigo", "Rofrano", "Roggiano Gravina", "Rogliano", "Rogno", "Roiate", "Roisan", "Rolo", "Romagnano al Monte", "Romagnese", "Romana", "Romano Canavese", "Romano di Lombardia", "Rombiolo", "Romentino", "Ronago", "Roncade", "Roncaro", "Roncello", "Ronchi Valsugana", "Ronciglione", "Ronco Biellese", "Ronco Canavese", "Roncobello", "Roncofreddo", "Roncone", "Rondissone", "Ronzo-Chienis", "Roppolo", "Rosà", "Rosasco", "Rosazza", "Roscigno", "Rosello", "Roseto degli Abruzzi", "Rosignano Marittimo", "Rosolina", "Rosora", "Rossana", "Rossano Veneto", "Rosta", "Rota Greca", "Rotello", "Rotondella", "Rottofreno", "Roure", "Rovasenda", "Rovegno", "Rovello Porro", "Roverchiara", "Roverè Veronese", "Roveredo in Piano", "Rovescala", "Roviano", "Rovito", "Rozzano", "Rubiana", "Ruda", "Rueglio", "Ruffia", "Rufina", "Ruino", "Ruoti", "Rutigliano", "Ruviano", "Ruvo di Puglia", "Sabbia", "Sabbioneta", "Saccolongo", "Sacrofano", "Sagama", "Sagrado", "Saint-Christophe", "Saint-Marcel", "Saint-Oyen", "Saint-Rhémy-en-Bosses", "Sala Baganza", "Sala Bolognese", "Sala Consilina", "Salandra", "Salara", "Salassa", "Salcedo", "Sale", "Sale Marasino", "Salemi", "Salerano Canavese", "Salerno", "Salgareda", "Salice Salentino", "Salisano", "Salle", "Salò", "Salsomaggiore Terme", "Saltrio", "Saluggia", "Saluzzo", "Salvirola", "Salza di Pinerolo", "Salzano", "Samassi", "Sambuca di Sicilia", "Sambuci", "Sammichele di Bari", "Samolaco", "Samone", "Samugheo", "San Bartolomeo in Galdo", "San Basile", "San Bassano", "San Benedetto Belbo", "San Benedetto del Tronto", "San Benedetto Po", "San Benedetto Val di Sambro", "San Bernardino Verbano", "San Biagio di Callalta", "San Biagio Saracinisco", "San Bonifacio", "San Calogero", "San Canzian d'Isonzo", "San Casciano dei Bagni", "San Cassiano", "San Cesareo", "San Cesario sul Panaro", "San Chirico Raparo", "San Cipriano d'Aversa", "San Cipriano Po", "San Colombano al Lambro", "San Colombano Certenoli", "San Cosmo Albanese", "San Costantino Calabro", "San Cristoforo", "San Damiano d'Asti", "San Daniele del Friuli", "San Demetrio Corone", "San Didero", "San Donaci", "San Donato di Ninea", "San Donato Val di Comino", "San Fedele Intelvi", "San Felice a Cancello", "San Felice del Benaco", "San Felice sul Panaro", "San Ferdinando di Puglia", "San Fili", "San Fior", "San Floriano del Collio", "San Francesco al Campo", "San Gavino Monreale", "San Genesio Atesino", "San Gennaro Vesuviano", "San Germano dei Berici", "San Gervasio Bresciano", "San Giacomo delle Segnate", "San Giacomo Vercellese", "San Gimignano", "San Giorgio a Cremano", "San Giorgio Albanese", "San Giorgio del Sannio", "San Giorgio delle Pertiche", "San Giorgio di Mantova", "San Giorgio di Pesaro", "San Giorgio in Bosco", "San Giorgio La Molara", "San Giorgio Monferrato", "San Giorgio Piacentino", "San Giorgio su Legnano", "San Giovanni a Piro", "San Giovanni Bianco", "San Giovanni del Dosso", "San Giovanni Gemini", "San Giovanni in Croce", "San Giovanni in Galdo", "San Giovanni in Persiceto", "San Giovanni la Punta", "San Giovanni Lupatoto", "San Giovanni Suergiu", "San Giovanni Valdarno", "San Giuliano di Puglia", "San Giuliano Terme", "San Giuseppe Vesuviano", "San Giusto Canavese", "San Gregorio da Sassola", "San Gregorio d'Ippona", "San Gregorio Matese", "San Lazzaro di Savena", "San Leonardo", "San Leucio del Sannio", "San Lorenzo", "San Lorenzo Bellizzi", "San Lorenzo di Sebato", "San Lorenzo in Campo", "San Lorenzo Maggiore", "San Luca", "San Lupo", "San Mango Piemonte", "San Marcellino", "San Marcello Pistoiese", "San Marco D'Alunzio", "San Marco Evangelista", "San Marco la Catola", "San Martino Alfieri", "San Martino Canavese", "San Martino dall'Argine", "San Martino di Finita", "San Martino di Venezze", "San Martino in Passiria", "San Martino in Rio", "San Martino Sannita", "San Martino sulla Marrucina", "San Marzano di San Giuseppe", "San Marzano sul Sarno", "San Maurizio Canavese", "San Mauro Castelverde", "San Mauro di Saline", "San Mauro La Bruca", "San Mauro Pascoli", "San Michele al Tagliamento", "San Michele di Ganzaria", "San Michele Mondovì", "San Miniato", "San Nazzaro", "San Nazzaro Val Cavargna", "San Nicola Arcella", "San Nicola da Crissa", "San Nicola la Strada", "San Nicolò d'Arcidano", "San Nicolò Gerrei", "San Pancrazio Salentino", "San Paolo Albanese", "San Paolo Cervo", "San Paolo di Civitate", "San Paolo Solbrito", "San Pier d'Isonzo", "San Piero a Sieve", "San Pietro a Maida", "San Pietro al Tanagro", "San Pietro Avellana", "San Pietro di Cadore", "San Pietro di Feletto", "San Pietro in Amantea", "San Pietro in Casale", "San Pietro in Gu", "San Pietro in Lama", "San Pietro Mosezzo", "San Pietro Val Lemina", "San Pietro Viminario", "San Polo dei Cavalieri", "San Polo di Piave", "San Ponso", "San Potito Sannitico", "San Prisco", "San Prospero", "San Quirino", "San Roberto", "San Romano in Garfagnana", "San Salvatore di Fitalia", "San Salvatore Telesino", "San Sebastiano al Vesuvio", "San Sebastiano da Po", "San Secondo Parmense", "San Severino Marche", "San Siro", "San Sostene", "San Sperate", "San Teodoro", "San Tomaso Agordino", "San Valentino Torio", "San Vendemiano", "San Vincenzo", "San Vincenzo Valle Roveto", "San Vito", "San Vito al Torre", "San Vito dei Normanni", "San Vito di Fagagna", "San Vito Lo Capo", "San Vito sullo Ionio", "San Vittore Olona", "San Zeno Naviglio", "San Zenone al Po", "Sanarica", "Sandrigo", "Sanfront", "Sangiano", "Sanguinetto", "Sannazzaro de' Burgondi", "Sannicola", "Sansepolcro", "Santa Caterina Albanese", "Santa Caterina Villarmosa", "Santa Cristina d'Aspromonte", "Santa Cristina Gela", "Santa Croce Camerina", "Santa Croce di Magliano", "Santa Domenica Talao", "Santa Elisabetta", "Santa Flavia", "Santa Giusta", "Santa Giustina in Colle", "Santa Lucia del Mela", "Santa Lucia di Serino", "Santa Margherita di Belice", "Santa Margherita Ligure", "Santa Maria a Vico", "Santa Maria Coghinas", "Santa Maria del Molise", "Santa Maria di Licodia", "Santa Maria Hoè", "Santa Maria la Carità", "Santa Maria La Longa", "Santa Maria Nuova", "Santa Marina Salina", "Santa Ninfa", "Santa Severina", "Santa Sofia D'Epiro", "Santa Teresa Gallura", "Santa Vittoria d'Alba", "Santadi", "Sant'Agata Bolognese", "Sant'Agata del Bianco", "Sant'Agata di Militello", "Sant'Agata Feltria", "Sant'Agata Li Battiati", "Sant'Agnello", "Sant'Albano Stura", "Sant'Alessio in Aspromonte", "Sant'Alfio", "Sant'Ambrogio di Valpolicella", "Sant'Anastasia", "Sant'Andrea Apostolo dello Ionio", "Sant'Andrea di Conza", "Sant'Angelo a Cupolo", "Sant'Angelo a Scala", "Sant'Angelo d'Alife", "Sant'Angelo del Pesco", "Sant'Angelo di Piove di Sacco", "Sant'Angelo in Pontano", "Sant'Angelo Le Fratte", "Sant'Angelo Lodigiano", "Sant'Angelo Muxaro", "Sant'Anna Arresi", "Sant'Antimo", "Sant'Antonino di Susa", "Sant'Antonio di Gallura", "Sant'Arcangelo", "Sant'Arcangelo Trimonte", "Sant'Arsenio", "Sant'Egidio alla Vibrata", "Sant'Elena", "Sant'Elia a Pianisi", "Sant'Elpidio a Mare", "Santeramo in Colle", "Sant'Eufemia d'Aspromonte", "Sant'Eusanio Forconese", "Santi Cosma e Damiano", "Sant'Ilario d'Enza", "Santo Stefano al Mare", "Santo Stefano d'Aveto", "Santo Stefano di Cadore", "Santo Stefano di Magra", "Santo Stefano di Sessanio", "Santo Stefano Lodigiano", "Santo Stefano Roero", "Santo Stino di Livenza", "Santomenna", "Sant'Omobono Terme", "Santopadre", "Santorso", "Santu Lussurgiu", "Sanza", "Saonara", "Sappada", "Saracena", "Sarcedo", "Sardara", "Sarego", "Sarezzano", "Sarmato", "Sarnano", "Sarno", "Saronno", "Sarroch", "Sarteano", "Sarule", "Sassano", "Sassello", "Sassinoro", "Sasso Marconi", "Sassofeltrio", "Sassuolo", "Satriano di Lucania", "Sauze di Cesana", "Sava", "Saviano", "Savignano Irpino", "Savignano sul Rubicone", "Savignone", "Savoca", "Savogna d'Isonzo", "Savona", "Scafati", "Scala", "Scaldasole", "Scalenghe", "Scampitella", "Scandiano", "Scandolara Ravara", "Scandriglia", "Scano di Montiferro", "Scanzano Jonico", "Scapoli", "Scarmagno", "Scarperia", "Scerni", "Scheggino", "Schiavon", "Schilpario", "Schivenoglia", "Sciara", "Scido", "Scilla", "Sciolze", "Sclafani Bagni", "Scopa", "Scoppito", "Scorrano", "Scurcola Marsicana", "Scurzolengo", "Secinaro", "Secugnago", "Sedico", "Sedini", "Sedrina", "Segariu", "Segni", "Segrate", "Selargius", "Selegas", "Sellero", "Sellia Marina", "Selva di Cadore", "Selva di Val Gardena", "Selve Marcone", "Semestene", "Seminara", "Senago", "Senale-San Felice", "Senerchia", "Senigallia", "Senise", "Senna Lodigiana", "Sennori", "Sepino", "Sequals", "Serdiana", "Seren del Grappa", "Seriate", "Serino", "Sermide", "Sernaglia della Battaglia", "Serole", "Serra de' Conti", "Serra Riccò", "Serra San Quirico", "Serracapriola", "Serralunga d'Alba", "Serramanna", "Serramezzana", "Serrapetrona", "Serrastretta", "Serravalle a Po", "Serravalle Langhe", "Serravalle Scrivia", "Serre", "Serri", "Serrungarina", "Servigliano", "Sessa Cilento", "Sessano del Molise", "Sestino", "Sesto al Reghena", "Sesto Campano", "Sesto Fiorentino", "Sestola", "Sestriere", "Settala", "Settime", "Settimo Rottaro", "Settimo Torinese", "Settingiano", "Seui", "Seveso", "Sezze", "Sgonico", "Siamaggiore", "Siano", "Sicignano degli Alburni", "Siddi", "Siena", "Signa", "Silanus", "Siligo", "Silius", "Sillavengo", "Silvano Pietra", "Simala", "Simbario", "Sinagra", "Sindia", "Sinio", "Sinnai", "Siracusa", "Siris", "Sirolo", "Siror", "Sissa", "Siziano", "Sluderno", "Smerillo", "Socchieve", "Sogliano al Rubicone", "Soglio", "Solagna", "Solaro", "Solarolo Rainerio", "Solbiate", "Solbiate Olona", "Soleminis", "Solesino", "Solferino", "Solignano", "Solonghello", "Solto Collina", "Somaglia", "Somma Lombardo", "Sommacampagna", "Sommariva Perno", "Sommo", "Soncino", "Sondrio", "Sonico", "Soprana", "Soraga", "Sorano", "Sorbo Serpico", "Sordevolo", "Soresina", "Sorgono", "Sorianello", "Soriano nel Cimino", "Soriso", "Sormano", "Sorrento", "Sortino", "Sospirolo", "Sostegno", "Sover", "Sovere", "Soveria Simeri", "Sovicille", "Sovizzo", "Sozzago", "Spadola", "Sparone", "Spello", "Sperlinga", "Sperone", "Spezzano Albanese", "Spezzano Piccolo", "Spigno Monferrato", "Spilamberto", "Spilinga", "Spinazzola", "Spineda", "Spineto Scrivia", "Spino d'Adda", "Spinoso", "Spoleto", "Spongano", "Sporminore", "Spresiano", "Squillace", "Staffolo", "Staiti", "Stanghella", "Statte", "Stazzema", "Stefanaconi", "Stella Cilento", "Stelvio", "Sternatia", "Stia", "Stigliano", "Stilo", "Stintino", "Stornara", "Storo", "Stradella", "Strambino", "Stregna", "Stresa", "Striano", "Strona", "Strongoli", "Stroppo", "Sturno", "Subbiano", "Succivo", "Suelli", "Suisio", "Sulmona", "Sumirago", "Suni", "Supersano", "Surano", "Susa", "Sustinente", "Sutri", "Suvereto", "Taceno", "Taggia", "Taglio di Po", "Taibon Agordino", "Taio", "Talamello", "Talana", "Talla", "Tambre", "Tarano", "Tarantasca", "Tarcento", "Tarsia", "Tarvisio", "Tassarolo", "Taurano", "Taurianova", "Tavagnacco", "Tavarnelle Val di Pesa", "Tavenna", "Tavernerio", "Tavernole sul Mella", "Tavigliano", "Tavullia", "Teano", "Teglio", "Telese Terme", "Telti", "Telve di Sopra", "Temù", "Tenno", "Teor", "Teramo", "Terelle", "Terenzo", "Terlago", "Terlizzi", "Termeno sulla Strada del Vino", "Termoli", "Ternengo", "Terno d'Isola", "Terragnolo", "Terranova da Sibari", "Terranova di Pollino", "Terranuova Bracciolini", "Terrassa Padovana", "Terrazzo", "Terricciola", "Tertenia", "Terzo", "Terzolas", "Tesero", "Tessennano", "Teti", "Teverola", "Thiene", "Tiana", "Ticineto", "Tiglieto", "Tignale", "Tione degli Abruzzi", "Tirano", "Tiriolo", "Tissi", "Tivoli", "Toano", "Tocco da Casauria", "Todi", "Toirano", "Tolfa", "Tollo", "Tolve", "Ton", "Tonara", "Tonengo", "Tora e Piccilli", "Torano Nuovo", "Torcegno", "Torchiarolo", "Torella del Sannio", "Torgnon", "Torino di Sangro", "Torlino Vimercati", "Tornareccio", "Tornimparte", "Tornolo", "Torpè", "Torralba", "Torrazza Piemonte", "Torre Annunziata", "Torre Boldone", "Torre Cajetani", "Torre d'Arese", "Torre de' Negri", "Torre de' Picenardi", "Torre del Greco", "Torre di Ruggiero", "Torre d'Isola", "Torre Mondovì", "Torre Pallavicina", "Torre San Giorgio", "Torre Santa Susanna", "Torrebelvicino", "Torrecuso", "Torregrotta", "Torrenova", "Torretta", "Torrevecchia Teatina", "Torri di Quartesolo", "Torriana", "Torricella", "Torricella in Sabina", "Torricella Sicura", "Torriglia", "Torrioni", "Torrita Tiberina", "Tortona", "Tortorella", "Tortorici", "Toscolano-Maderno", "Tovo di Sant'Agata", "Trabia", "Tramatza", "Tramonti", "Tramonti di Sotto", "Trana", "Transacqua", "Trapani", "Trarego Viggiona", "Trasaghis", "Tratalias", "Travacò Siccomario", "Travedona-Monate", "Traversetolo", "Travesio", "Trebaseleghe", "Trecasali", "Trecastagni", "Trecchina", "Tredozio", "Tregnago", "Treiso", "Tremestieri Etneo", "Tremosine", "Trentinara", "Trentola-Ducenta", "Treppo Carnico", "Trepuzzi", "Tres", "Trescore Balneario", "Tresigallo", "Tresnuraghes", "Trevi", "Trevico", "Trevignano", "Treville", "Treviso", "Trezzano Rosa", "Trezzo sull'Adda", "Trezzone", "Tribiano", "Tricarico", "Tricerro", "Trichiana", "Trieste", "Trigolo", "Trinità d'Agultu e Vignola", "Trino", "Tripi", "Trissino", "Trivento", "Trivigliano", "Trivigno", "Trodena nel parco naturale", "Troia", "Tromello", "Tronzano Lago Maggiore", "Tropea", "Truccazzano", "Tuenno", "Tufillo", "Tufo", "Tuili", "Tuoro sul Trasimeno", "Turano Lodigiano", "Turbigo", "Turri", "Turrivalignani", "Tusa", "Ubiale Clanezzo", "Ucria", "Ugento", "Uggiate-Trevano", "Ulassai", "Umbertide", "Urago d'Oglio", "Urbana", "Urbe", "Urbisaglia", "Uri", "Urzulei", "Usellus", "Usmate Velate", "Ussaramanna", "Usseaux", "Ussita", "Uta", "Vaccarizzo Albanese", "Vacri", "Vado Ligure", "Vaglia", "Vaglio Serra", "Vaiano Cremasco", "Vailate", "Vajont", "Val di Nizza", "Val Masino", "Valbondione", "Valbrevenna", "Valda", "Valdaora", "Valdengo", "Valdidentro", "Valdina", "Valdobbiadene", "Valeggio", "Valentano", "Valenzano", "Valfabbrica", "Valfloriana", "Valganna", "Valgoglio", "Valgreghentino", "Valguarnera Caropepe", "Vallanzengo", "Vallata", "Valle Aurina", "Valle dell'Angelo", "Valle di Casies", "Valle Lomellina", "Valle Salimbene", "Vallebona", "Vallecrosia", "Valledoria", "Vallelonga", "Vallemaio", "Vallerano", "Vallerotonda", "Valleve", "Vallinfreda", "Vallo della Lucania", "Vallo Torinese", "Valmacca", "Valmala", "Valmorea", "Valnegra", "Valperga", "Valsavarenche", "Valsinni", "Valstagna", "Valtopina", "Valtournenche", "Valvasone", "Valverde", "Vandoies", "Vanzago", "Vaprio d'Adda", "Varallo", "Varano Borghi", "Varapodio", "Varco Sabino", "Varena", "Varese", "Varisella", "Varna", "Varzi", "Vas", "Vasia", "Vastogirardi", "Vauda Canavese", "Vazzola", "Vedano al Lambro", "Veddasca", "Vedeseta", "Veggiano", "Veglio", "Veleso", "Velletri", "Velo d'Astico", "Velturno", "Venaria Reale", "Venasca", "Vendone", "Venegono Inferiore", "Venetico", "Veniano", "Venticano", "Ventimiglia di Sicilia", "Venzone", "Verano Brianza", "Verbicaro", "Verceia", "Vercurago", "Verdello", "Verderio Superiore", "Vergato", "Verghereto", "Vermezzo", "Vernante", "Vernate", "Vernio", "Verolanuova", "Verolengo", "Verona", "Verrayes", "Verretto", "Verrua Po", "Vertemate con Minoprio", "Verucchio", "Vervio", "Verzegnis", "Verzuolo", "Vescovato", "Vespolate", "Vestenanova", "Vestone", "Vetralla", "Vezza d'Alba", "Vezzano", "Vezzano sul Crostolo", "Viadana", "Viagrande", "Vialfrè", "Viareggio", "Vibo Valentia", "Vicalvi", "Vicchio", "Vico Canavese", "Vico Equense", "Vicoforte", "Vicolungo", "Vicovaro", "Vidigulfo", "Vidracco", "Vietri di Potenza", "Viganella", "Vigano San Martino", "Vigasio", "Viggianello", "Viggiù", "Vigliano Biellese", "Vignale Monferrato", "Vignate", "Vignola-Falesina", "Vignolo", "Vigo di Cadore", "Vigo Rendena", "Vigolo", "Vigolzone", "Vigonovo", "Viguzzolo", "Villa Bartolomea", "Villa Biscossi", "Villa Castelli", "Villa Collemandina", "Villa d'Adda", "Villa del Bosco", "Villa di Briano", "Villa di Serio", "Villa d'Ogna", "Villa Faraldi", "Villa Lagarina", "Villa Literno", "Villa Poma", "Villa San Giovanni", "Villa San Pietro", "Villa Santa Lucia", "Villa Santa Maria", "Villa Sant'Antonio", "Villa Santo Stefano", "Villa Vicentina", "Villabate", "Villacidro", "Villadose", "Villafalletto", "Villafranca di Verona", "Villafranca Padovana", "Villafranca Sicula", "Villafrati", "Villagrande Strisaili", "Villalba", "Villalvernia", "Villamaina", "Villamarzana", "Villamiroglio", "Villanova Biellese", "Villanova d'Albenga", "Villanova d'Asti", "Villanova del Ghebbo", "Villanova di Camposampiero", "Villanova Mondovì", "Villanova Monteleone", "Villanova sull'Arda", "Villanova Tulo", "Villanovafranca", "Villanuova sul Clisi", "Villapiana", "Villar Dora", "Villar Pellice", "Villar San Costanzo", "Villarboit", "Villaricca", "Villarosa", "Villasanta", "Villasor", "Villastellone", "Villaurbana", "Villaverla", "Villesse", "Villette", "Villongo", "Vilminore di Scalve", "Vimodrone", "Vinchiaturo", "Vinci", "Vinzaglio", "Vione", "Virgilio", "Visano", "Visciano", "Visone", "Vistarino", "Vita", "Viticuso", "Vitorchiano", "Vittorio Veneto", "Vittuone", "Vitulazio", "Vivaro", "Viverone", "Vizzola Ticino", "Vo'", "Vobbia", "Vodo Cadore", "Voghiera", "Volano", "Volongo", "Volpara", "Volpeglino", "Volta Mantovana", "Voltago Agordino", "Voltido", "Volturara Irpina", "Volvera", "Zaccanopoli", "Zagarise", "Zambana", "Zandobbio", "Zanica", "Zavattarello", "Zeddiani", "Zelo Buon Persico", "Zeme", "Zenson di Piave", "Zerbo", "Zerfaliu", "Zermeghedo", "Zevio", "Ziano Piacentino", "Zibido San Giacomo", "Zimella", "Zinasco", "Zocca", "Zola Predosa", "Zollino", "Zoppè di Cadore", "Zovencedo", "Zuccarello", "Zugliano", "Zumaglia", "Zungoli"};
    do {
      comune = name[random.nextInt(name.length)];
    } while (!(comune.matches(Donatore.LUOGONASCITA_REGEX)));
    return comune;
  }
*/
  /**
   * Metodo che genera una data di nascita tra il 1900 e 2020.
   *
   * @return data di nascita casuale
   */
  public static String generateDataDiNascita() {
    String gg;
    String mm;
    String aaaa;
    String test;
    do {
      test = gg = mm = aaaa = "";
      GregorianCalendar gc = new GregorianCalendar();
      int year = randBetween(1900, 2020);
      gc.set(gc.YEAR, year);
      int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
      gc.set(gc.DAY_OF_YEAR, dayOfYear);
      gg = String.valueOf(gc.get(gc.DAY_OF_MONTH));
      mm = String.valueOf(gc.get(gc.MONTH) + 1);
      aaaa = String.valueOf(gc.get(gc.YEAR));
      test = gg + "/" + mm + "/" + aaaa;
    } while (!(test.matches(Donatore.DATANASCITA_REGEX)));
    return gg + "/" + mm + "/" + aaaa;
  }

  /**
   * Metodo che genera un nome o cognome random.
   *
   * @return nome o cognome valido generato casualmente
   */
  public static String generateNomeOCognome() {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rand = new Random();
    StringBuilder builder;
    do {
      builder = new StringBuilder();
      while (builder.toString().length() == 0) {
        int length = rand.nextInt(5) + 5;
        for (int i = 0; i < length; i++) {
          builder.append(letters.charAt(rand.nextInt(letters.length())));
        }
      }
    } while (!(builder.toString().matches(Utente.NOME_COGNOME_REGEX)));
    return builder.toString();
  }

  /**
   * Metodo che genrea un codice fiscale valido.
   *
   * @return codice fiscale genrato casualmente
   */
  public static String generateCoidceFiscale() {
    String numbers = "0123456789";
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int len = 16;
    Random rnd = new Random();
    StringBuilder sb = null;
    do {
      sb = new StringBuilder(len);
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    } while (!(sb.toString().matches(Utente.CF_REGEX)));
    return sb.toString();

  }

  /**
   * Metodo che genera una email valida.
   *
   * @return email valida
   */
  public static String generateRandomEmail() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();

    String saltStr;
    do {
      while (salt.length() < 10) { // length of the random string.
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
      }
      saltStr = salt.toString() + "@gmail.com";
    } while (!saltStr.matches(Utente.EMAIL_REGEX));
    return saltStr;
  }

  /**
   * @param len deve essere tra 8 e 16
   * @return password generata random di lunghezza len
   * @throws IllegalArgumentException
   */
  public static String generateRandomPassword(int len) throws IllegalArgumentException {
    if (!(len >= 8 && len <= 16)) {
      throw new IllegalArgumentException("parametro len non è compreso tra 8 e 16 ");
    }
    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
            + "lmnopqrstuvwxyz!@#$%&";
    Random rnd = new Random();
    StringBuilder sb;
    do {
      sb = new StringBuilder(len);
      for (int i = 0; i < len; i++)
        sb.append(chars.charAt(rnd.nextInt(chars.length())));
    } while (!sb.toString().matches(Utente.PASSWORD_REGEX));
    return sb.toString();

  }

  /**
   * Metodo che ritorna un valore casuale tra POS e NEG.
   *
   * @return valore casuale POS o NEG
   */
  public static String generateRH() {
    Random random = new Random();
    if (random.nextBoolean())
      return "POS";
    else
      return "NEG";
  }

  public static int randBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }

  /**
   * Metodo che restituisce l'ora corrente.
   *
   * @return Time timestamp dell'ora corrente
   */
  public static Time getThisTime() {
    return new Time(new GregorianCalendar().getTimeInMillis());
  }

  /**
   * Metodo che Time con l'ora impostata come parametro.
   *
   * @param ora    numero di ore
   * @param minuti numero di munuti
   * @return Time Time con l'ora impostata come parametro
   */
  public static Time getThisTime(int ora, int minuti) {
    ora--;
    return new Time(ora * 3600000 + (minuti * 60000));
  }

}
