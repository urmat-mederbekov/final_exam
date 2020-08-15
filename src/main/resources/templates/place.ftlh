<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="row">
        <div class="col">
            <div class="row"><h1>${images[0].place.title}</h1></div>
            <div class="col">${images[0].place.description}</div>
        </div>
        <div class="col">
            <img class="img-fluid" src="/places/image/${images[0].name}" alt="image">
        </div>
    </div>
    <div class="row"><h3>Rating ${images[0].place.rating?string["0.#"]}</h3></div>
    <br>
    <div class="d-flex flex-row">
    <#list images as image>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="/places/image/${image.name}" alt="Card image cap">
        </div>
    </#list>
    </div>
    <br>
    <#list items as review>
        <div class="card">
            <div class="card-header">
                <p>В ${review.dateTime} ${review.user.name} сказал(a):</p>
            </div>
            <div class="card-body">
                <p class="card-text">${review.description}</p>
            </div>
            <div class="card-body">
                <p class="card-text">Оценка: ${review.rating}</p>
            </div>
        </div>
    </#list>
    <#include "partials/pagination.ftlh">
    <#if user??>
        <br>
        <form class="box-1" action="/places/${images[0].place.id?c}/reviews" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3 class="text-center">Добавить Отзыв</h3>
            <div class="form-group">
                <textarea class="form-control" rows="4" cols="50" name="description" placeholder="Описание" required></textarea>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'description'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <select class="form-control"  name="rating" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <input type="hidden" name="placeId" value="${images[0].place.id?c}">
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
        <br>
        <form class="box-1" enctype="multipart/form-data" action="/places/${images[0].place.id?c}/images" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3 class="text-center">Добавить Фотографию</h3>
            <div class="form-group">
                <input class="form-control" type="file" name="image" required>
            </div>
            <input type="hidden" name="placeId" value="${images[0].place.id?c}">
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
    </#if>
</@main.renderWith>