<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Who's winning?</title>
    <link rel="stylesheet" href="css/candidates.css">
</head>

<body>
    <div class="container flex flex-col align-center bg-danger">
    <h1>Who's winning?</h1>
    <main class="flex flex-wrap align-center">
     <#list candidades as candidate>
    <div class="card">
        <div class="flex flex-col align-center" href="#">
        <img src="images/${candidate.photo}">
        <p>Votes: ${candidate.percentageRatio}%</p>
         <#if candidate.alreadyVoted == 1>
           <p>You voted for ${candidate.name}</p>
          </#if>
      </div>
    </div>
    </#list>
    <a class="back flex align-center" href="/">back to main</a>
    </main>
    </div>
</body>

</html>