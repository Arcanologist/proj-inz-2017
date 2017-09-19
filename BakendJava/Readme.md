1.Technologie
Wykorzystywane technologie w przykładzie: java 1.7,kontener springa,junit,spring mvc,spring rest
2.Narzędzia
Wykorzystywane narzędzia: maven, Eclipse EE IDE
3.Uruchomienie i konfiguracja
Uruhomienie w eclipse: 
Wybrać w Eclipse import->existing maven project->wskazać root projektu i nastepnie zaznaczyć wszystkie pliki pom.xml od projektów.
Następnie prawy myszy na dowolnym projekcie w project explorer i wybrać maven -> update project -> zaznaczyć wszystkie projekty. Maven będzie ściągał źródła oraz sprawi że moduły będą widoczne jako artefakty.
W eclipse dodać serwer Tomcat 7.0 poprzez: perferences -> server i po dodaniu w zakładce server trzeba dodać serwer który już później będzie zapamiętany. Uruchomić serwer bez projektu i sprawdzić czy uruchomił się (standardowy port 8080).
Następnie dodać projekt poprzez :prawy myszy na serwerze i Add or remove.
4.Struktura
Projekt składa się z 4 częsci: parent - zawiera zależnosci i jest rootem dla pozostałych, service - zawiera logikę, web - zawiera kontroller, client - łęczy się z innym serwisem restowym.
5.Adnotacje w kontrolerze REST
@RestController - adnotacja nad klasą - oznacza ze klasa jest kontrolerem restowym
@RequestMapping - mapowanie ścieżek wywołań
@PathVariable - pobiera ze scieżki zmienną
@Autowired - wiązanie springowych beanów