package com.sbz.bank.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class Location {
	@Column
	double longitude;

	@Column
	double latitude;

	public double getDistance(Location other) {
		double longitudeRad = degreeToRadian(this.getLongitude());
		double latitudeRad = degreeToRadian(this.getLatitude());
		double otherLongitudeRad = degreeToRadian(other.getLongitude());
		double otherLatitudeRad = degreeToRadian(other.getLatitude());

		double dLong = longitudeRad - otherLongitudeRad;
		double dLat = latitudeRad - otherLatitudeRad;

		double ans = Math.pow(Math.sin(dLat/2), 2) + Math.cos(latitudeRad) * Math.cos(otherLatitudeRad) * Math.pow(Math.sin(dLong/2), 2);
		ans = 2 * Math.asin(Math.sqrt(ans));
		double earthRadius = 6371; // in km
		return ans * earthRadius;
	}

	private double degreeToRadian(double degree) {
		return (Math.PI / 180) * degree;
	}
}
