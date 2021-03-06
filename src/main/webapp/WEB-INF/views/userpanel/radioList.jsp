<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.itaki.radyodinlenir.util.PageUtils"%>
<c:set var="currentURL"
	value="${PageUtils.getCurentFullPath(pageContext.request)}" />
<section class="events-finder">
	<div class="container">
	
		<header>
			<div class="row">
				<div class="col-xs-12 col-md-7">
					<h2 class="text-uppercase"><c:out value="${iTitle}"></c:out> </h2>
				</div>

				<div class="col-xs-12 col-md-5">
					<div class="event-form text-right">
						<form action="/search" method="get">
							<div class="form-input search-keyword">
								<input id="searchText" name="searchText" type="text"
									placeholder="Radyo Ara" value="${searchedText}"> <i
									class="icon fa fa-search"></i>
							</div>
							<button class="btn btn-default" type="submit">Radyo Ara</button>
						</form>
					</div>
				</div>
			</div>
		</header>
		<div align="center" id="paginationtop"><c:out value="${adpaginationtop.description}" escapeXml="false"></c:out></div>
		<div class="featured-events">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${empty radioList}">
						<div class="alert alert-danger">
							<h3>Herhangi bir radyo bulunamadı !!</h3>
						</div>
					</c:if>
					<c:if test="${not empty radioList}">
						<div class="store-grid text-uppercase text-bold">
							<c:forEach var="radio" items="${radioList}">
								<div class="store-product col-md-2">
									<figure>
										<img src="${baseURL}/resources/radiologos/${radio.logo}" alt="${radio.name}">
										<figcaption>
											<a href="/station/${radio.cleanUrl}" class="btn btn-grey"><i
												class="fa fa-ticket "></i> Dinle</a>
										</figcaption>
									</figure>
									<div class="product-info">
										<h3>${radio.name}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>

				</div>
				<!--column-->
			</div>
			<c:if test="${pageIndex != null && maxPageIndex != 1}">
				<div class="row">
					<div class="col-xs-12">
						<ul class="pagination" >
							<c:url value="${pagerBaseUrl}/${pageIndex-1}" var="prev">
							</c:url>
							<c:if test="${pageIndex > 1}">
								<li><a class="prev page-numbers"
									href="<c:out value="${prev}" />">«</a></li>
							</c:if>
							<c:if test="${pageIndex <= 4}">
								<c:forEach begin="1"
									end="${((pageIndex+4) <= maxPageIndex ? 7 : maxPageIndex)}"
									step="1" varStatus="i">
									<c:choose>
										<c:when test="${pageIndex == i.index}">
											<li class='active'><span>${i.index}</span></li>
										</c:when>
										<c:otherwise>
											<c:url value="${pagerBaseUrl}/${i.index}" var="url">
											</c:url>
											<li><a href="${url}">${i.index}</a></li>

										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${pageIndex > 4}">
								<c:forEach begin="${pageIndex-4}"
									end="${((pageIndex+4) <= maxPageIndex ? (pageIndex+3) : maxPageIndex)}"
									step="1" varStatus="i">
									<c:choose>
										<c:when test="${pageIndex == i.index}">
											<li class='active'><span>${i.index}</span></li>
										</c:when>
										<c:otherwise>
											<c:url value="${pagerBaseUrl}/${i.index}" var="url">
											</c:url>
											<li><a href="${url}">${i.index}</a></li>

										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>

							<c:url value="${pagerBaseUrl}/${pageIndex+1}" var="next">
							</c:url>
							<c:if test="${pageIndex + 1 <= maxPageIndex}">
								<li><a class='next page-numbers' href="${ next}">»</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</section>