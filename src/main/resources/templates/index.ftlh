<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <form class="box-1"action="/search">
        <input type="text" name="name">
        <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
    </form>
    <div class="d-flex flex-row">
        <#list items as image>
                <div class="card" style="width: 18rem;">
                    <a href="/places/${image.place.id?c}">
                    <img class="card-img-top" src="/places/image/${image.name}" alt="Card image cap">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">${image.place.title}</h5>
                        <p class="card-text">Оценка ${image.place.rating}</p>
                    </div>
                </div>
        </#list>
    </div>
    <#include "partials/pagination.ftlh">
</@main.renderWith>