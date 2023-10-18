package android.app.lotus.types

data class Article (
    val title: String,
    val markdownContent: String
)

val exampleArticle = Article(
    title = "Backgrund",
    markdownContent = """
## Alkohol och narkotika finns på arbetet

Det finns beräkningar som säger att 10–15 procent av männen och 3–5 procent av kvinnorna på en arbetsplats missbrukar alkohol, narkotika eller läkemedel i någon form. Det är viktigt att inte blunda för alkohol- och drogrelaterade problem på våra arbetsplatser. Utbildning och verksamhetsstruktur är grunden för ett effektivt handlingsprogram mot beroendeframkallande preparat, som alkohol, narkotika och tabletter.

Mönstren liknar varandra i de flesta typer av beroende men eftersom alkohol, i motsats till narkotika, är lagligt och lättillgängligt är problem med alkohol mer utbrett. Därför fördjupar vi oss mer i alkoholrelaterade exempel i denna utbildning.

## Vem drabbas?

När det gäller alkohol är gränserna mellan att dricka normalt och att falla igenom små. Det kan handla om oförsiktighet, okunskap, vana eller tradition. Om semester, stress, ångest, levnadskris, umgängesvanor eller representation. De flesta människor klarar att dricka alkohol utan att få problem och utan att hälsan på sikt påverkas negativt i nämnvärd grad. Andra hamnar på en konsumtionsnivå som kan betraktas som riskabel eller rentav som ett missbruk. 

Det är inte bara uttalade "missbrukare" som skadas av alkohol. De flesta som drabbas av alkoholrelaterade sjukdomar anses vara skötsamma och i de flesta fall har de jobb.

## Stora kostnader för samhället och företagen 

Totalt kostar alkoholmissbrukets följder det svenska samhället omkring 150 miljarder varje år. Missbruket leder till minskad effektivitet, ökad frånvaro och psykisk och fysisk ohälsa bland alla anställda. Beräkningar visar att missbruket kostar företagen minst 2,5 procent av lönesumman. Det betyder att en organisation med 500 anställda har en kostnad för missbruk på cirka 4 miljoner kronor per år. Det handlar bland annat om kostnader för vikarier, övertids- eller mertidsersättning för övriga anställda och rehabiliteringskostnader.

## Samarbete och respekt

Arbetsgivarens och arbetskamraternas attityd är viktig för att komma tillrätta med eventuella missbruksproblem. Det bör klart framgå i organisationens handlingsprogram att arbetsplatsen ska vara alkohol- och drogfri. Handlingsprogrammet ska arbetas fram gemensamt, av arbetsgivare och fack och diskuteras på möten med alla anställda, så att frågorna växer in i organisationen. Annars bryr sig ingen om dem. 

När det gäller att få bort ett missbruksproblem på jobbet är det viktigt att den som missbrukar, arbetsgivaren och facket arbetar tillsammans. Utgångspunkten måste vara att missbrukaren vill bli fri från sitt missbruk och hans eller hennes integritet måste skyddas om resultatet ska bli bra.
        """
)