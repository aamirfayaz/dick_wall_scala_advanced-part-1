def weatherLookUp(cityCode: Option[String]): Int = {
  cityCode.toList.map(_.toInt).sum
}