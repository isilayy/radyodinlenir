<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.itaki.radyodinlenir.util.PageUtils"%>
<c:set var="baseURL"	value="${PageUtils.getHost(pageContext.request)}" />
<section class="events-finder">
	<div class="container">
		<header>
			<div class="row">
				<div class="col-xs-12 col-md-8">
					<h2 class="text-uppercase">Popüler Radyolar</h2>
				</div>
				<div class="col-xs-12 col-md-4">
					<div class="event-form text-right">
						<form action="/search" method="get">
							<div class="form-input search-keyword">
								<input id="searchText" name="searchText" type="text" placeholder="Radyo Ara" value="${searchedText}"> <i class="icon fa fa-search"></i>
							</div>
							<button class="btn btn-default" type="submit">Radyo Ara</button>
						</form>
					</div>
				</div>
			</div>
		</header>
		<div class="featured-events">
			<div class="row">
				<div class="col-xs-12">
					<div class="store-grid text-uppercase text-bold">
						<c:forEach var="popRadio" items="${popularRadios}">
							<div class="store-product">
								<figure>
									<img width="152" height="152" src="${baseURL}/resources/radiologos/${popRadio.logo}"  alt="${radio.name}">
									<figcaption>
										<a href="${baseURL}/station/${popRadio.cleanUrl}" class="btn btn-grey"><i class="fa fa-ticket "></i> Dinle</a>
									</figcaption>
								</figure>
								<div class="product-info">
									<h3>${popRadio.name}</h3>
									
								</div>
							</div>
						</c:forEach>
					</div>
					<a class="btn btn-wide btn-grey text-uppercase text-bold" href="${baseURL}/popularStations">Daha
						Fazlasını Gör</a>
				</div>
				<!--column-->
			</div>
		</div>
	</div>
</section>
<section>
	<header class="parallax parallax_two style3 text-center text-uppercase text-bold"
		data-stellar-background-ratio="0.5">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 section-block">
					<h2>Son Eklenen Radyolar</h2>
					<p>
						Radyo İstasyonunuzu Bizle <a href="#">Paylaşın</a>
					</p>
				</div>
			</div>
		</div>
	</header>
	<!--section header-->
	<div class="photography">
		<div class="container color-white">
			<div class="store-grid text-uppercase text-bold clearfix">

				<c:forEach var="lastRadio" items="${newestRadios}">
					<div class="store-product">
						<figure>
							<img width="152" height="152" src="${baseURL}/resources/radiologos/${lastRadio.logo}"  alt="${radio.name}">
							<figcaption>
								<a href="${baseURL}/station/${lastRadio.cleanUrl}" class="btn btn-grey"><i class="fa fa-ticket "></i> Dinle</a>
							</figcaption>
						</figure>
						<div class="product-info">
							<h3>${lastRadio.name}</h3>
						</div>
					</div>
				</c:forEach>
			</div>
			<a class="btn btn-wide btn-grey text-uppercase text-bold" href="${baseURL}/newestStations">Daha
				Fazlasını Gör</a>

		</div>
		<!--container-->
	</div>
</section>