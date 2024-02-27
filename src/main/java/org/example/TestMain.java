package org.example;

import net.opengis.wfs.DescribeFeatureTypeType;
import net.opengis.wfs.FeatureCollectionType;
import net.opengis.wfs.GetFeatureType;
import net.opengis.wfs.QueryType;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.filter.Filter;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.collection.BaseFeatureCollection;
import org.geotools.feature.collection.SimpleFeatureIteratorImpl;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.wfs.v1_1.WFSConfiguration;
import org.geotools.xsd.Configuration;
import org.geotools.xsd.Encoder;
import org.geotools.xsd.Parser;
import org.locationtech.jts.geom.*;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMain {

    String req = "<wfs:GetFeature service=\"WFS\" version=\"1.1.0\"\n" +
                 "  xmlns:topp=\"http://www.openplans.org/topp\"\n" +
                 "  xmlns:wfs=\"http://www.opengis.net/wfs\"\n" +
                 "  xmlns=\"http://www.opengis.net/ogc\"\n" +
                 "  xmlns:gml=\"http://www.opengis.net/gml\"\n" +
                 "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                 "  xsi:schemaLocation=\"http://www.opengis.net/wfs\n" +
                 "                      http://schemas.opengis.net/wfs/1.1.0/wfs.xsd\">\n" +
                 "  <wfs:Query typeName=\"topp:states\">\n" +
                 "    <Filter>\n" +
                 "      <Intersects>\n" +
                 "        <PropertyName>the_geom</PropertyName>\n" +
                 "          <gml:Point srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">\n" +
                 "            <gml:coordinates>-74.817265,40.5296504</gml:coordinates>\n" +
                 "          </gml:Point>\n" +
                 "        </Intersects>\n" +
                 "      </Filter>\n" +
                 "  </wfs:Query>\n" +
                 "</wfs:GetFeature>";

    public void parse() throws IOException, ParserConfigurationException, SAXException {
        Conf conf = new Conf();
        Configuration configuration = new org.geotools.filter.v1_1.OGCConfiguration();
        Configuration configuration1 = new WFSConfiguration();
        Parser parser = new Parser(configuration1);
        String xml = "<Filter xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                     "  xmlns:ogc=\"http://www.opengis.net/ogc\"\n" +
                     "  xmlns=\"http://www.opengis.net/ogc\"\n" +
                     "  xsi:schemaLocation=\"http://www.opengis.net/ogc filter.xsd\">\n" +
                     "  <PropertyIsEqualTo>\n" +
                     "    <PropertyName>testString</PropertyName>\n" +
                     "    <Literal>2</Literal>\n" +
                     "  </PropertyIsEqualTo>\n" +
                     "</Filter>";
        String xml2 = """
                    <Filter xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:ogc="http://www.opengis.net/ogc"
                  xmlns="http://www.opengis.net/ogc"
                  xmlns:gml="http://www.opengis.net/gml"
                  xsi:schemaLocation="http://www.opengis.net/ogc filter.xsd">      <Intersects>
                        <PropertyName>geom</PropertyName>
                          <gml:Point>
                            <gml:coordinates>1,1</gml:coordinates>
                          </gml:Point>
                        </Intersects>
                      </Filter>
                """;
        String xml3 = """
                 <Filter xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:ogc="http://www.opengis.net/ogc"
                  xmlns="http://www.opengis.net/ogc"
                  xmlns:gml="http://www.opengis.net/gml"
                  xsi:schemaLocation="http://www.opengis.net/ogc filter.xsd">
                      <FeatureId fid="4234"/>
                      <FeatureId fid="4284"/>
                      <FeatureId fid="4332"/>
                    </Filter>
                """;
        String xml4 = """
                <Filter xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:ogc="http://www.opengis.net/ogc"
                  xmlns="http://www.opengis.net/ogc"
                  xmlns:gml="http://www.opengis.net/gml"
                  xsi:schemaLocation="http://www.opengis.net/ogc filter.xsd">
                      <Intersects>
                        <PropertyName/>
                        <gml:Polygon srsDimension="2"  xmlns:gml="http://www.opengis.net/gml" >
                          <gml:exterior>
                            <gml:LinearRing>
                              <gml:posList>59.92170475446907 30.349146156187217 59.92170475446907 30.349265308503952 59.92176447152672 30.349265308503952 59.92176447152672 30.349146156187217 59.92170475446907 30.349146156187217</gml:posList>
                            </gml:LinearRing>
                          </gml:exterior>
                        </gml:Polygon>
                      </Intersects>
                    </Filter>
                """;
        String xml5 = """
                <?xml version="1.0" encoding="UTF-8"?><GetFeature service="WFS" maxFeatures="10000" version="1.1.0" >
                  <Query  typeName="greenStuff:bushes">
                    <Filter>
                      <Intersects>
                        <PropertyName/>
                        <gml:Polygon srsDimension="2"  xmlns:gml="http://www.opengis.net/gml" >
                          <gml:exterior>
                            <gml:LinearRing>
                              <gml:posList>59.92170475446907 30.349146156187217 59.92170475446907 30.349265308503952 59.92176447152672 30.349265308503952 59.92176447152672 30.349146156187217 59.92170475446907 30.349146156187217</gml:posList>
                            </gml:LinearRing>
                          </gml:exterior>
                        </gml:Polygon>
                      </Intersects>
                    </Filter>
                  </Query>
                </GetFeature>

                               
                                """;



        InputStream targetStream2 = new ByteArrayInputStream(xml5.getBytes());

        InputStream targetStream = new ByteArrayInputStream(xml4.getBytes());

        Filter filter = (Filter) parser.parse(targetStream);
        GetFeatureType getFeatureType = (GetFeatureType) parser.parse(targetStream2);
        System.out.println(filter.toString());
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        QueryType query = (QueryType) getFeatureType.getQuery()
                .get(0);
        Encoder encoder=new Encoder(configuration1);
        encoder.encodeAsString(query,new QName("http://www.opengis.net/wfs", "QueryType"));
        Coordinate coord = new Coordinate(1, 1);
        Point point = geometryFactory.createPoint(coord);
        System.out.println(filter.evaluate(point));

        Coordinate[] coords =
                new Coordinate[]{new Coordinate(0, 0), new Coordinate(100, 0),
                        new Coordinate(100, 100), new Coordinate(0, 100), new Coordinate(0, 0)};
        LinearRing ring = geometryFactory.createLinearRing(coords);
        LinearRing holes[] = null; // use LinearRing[] to represent holes
        Geometry polygon = geometryFactory.createPolygon(ring, holes);
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("polygon");
        builder.add("geom", Geometry.class);
        SimpleFeatureType simpleFeatureType = builder.buildFeatureType();

        SimpleFeatureBuilder builder1 = new SimpleFeatureBuilder(simpleFeatureType);
        builder1.add(polygon);

        SimpleFeature simpleFeature = builder1.buildFeature("4234");
        FeatureCollection<SimpleFeatureType,SimpleFeature> collection= new BaseFeatureCollection<>() {
            @Override
            public FeatureIterator<SimpleFeature> features() {
                return new SimpleFeatureIteratorImpl(List.of(simpleFeature));
            }
        };
        try (FeatureIterator<SimpleFeature> iterator =collection.features()) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();
                System.out.println(query.getFilter().evaluate(feature));
                // process feature
            }
        }


    }

    void parse2() throws IOException, ParserConfigurationException, SAXException {
        Configuration configuration1 = new WFSConfiguration();
        Parser parser = new Parser(configuration1);
        String xml6= """
                <?xml version="1.0" encoding="UTF-8"?><wfs:FeatureCollection xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:MO="http://www.opengeospatial.net/mo" xmlns:wfs="http://www.opengis.net/wfs" xmlns:gml="http://www.opengis.net/gml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" numberOfFeatures="1" timeStamp="2024-02-22T11:48:19.024Z" xsi:schemaLocation="http://www.opengis.net/wfs http://127.0.0.1:8083/geoserver/schemas/wfs/1.1.0/wfs.xsd http://www.opengeospatial.net/mo http://127.0.0.1:8083/geoserver/wfs?service=WFS&amp;version=1.1.0&amp;request=DescribeFeatureType&amp;typeName=MO%3ASquare">
                  <gml:featureMembers>
                    <MO:Square gml:id="Square.288">
                      <MO:Sys>37</MO:Sys>
                      <MO:Adres>ул. Боровая, д. 6-8</MO:Adres>
                      <MO:TypePokrit>9</MO:TypePokrit>
                      <MO:squareType>1</MO:squareType>
                      <MO:idBranch>5</MO:idBranch>
                      <MO:prinadlezhnost>МО Владимирский округ</MO:prinadlezhnost>
                      <MO:functions>не определена</MO:functions>
                      <MO:geom>
                        <gml:MultiSurface  srsDimension="2">
                          <gml:surfaceMember>
                            <gml:Polygon>
                              <gml:exterior>
                                <gml:LinearRing>
                                  <gml:posList>59.92170746 30.34925097 59.92175765 30.34914853 59.92177466 30.34911382 59.92180386 30.34905421 59.92188356 30.34920518 59.92178995 30.34940911 59.92170746 30.34925097</gml:posList>
                                </gml:LinearRing>
                              </gml:exterior>
                            </gml:Polygon>
                          </gml:surfaceMember>
                        </gml:MultiSurface>
                      </MO:geom>
                      <MO:on_balance>1</MO:on_balance>
                      <MO:id_inv_status>2</MO:id_inv_status>
                    </MO:Square>
                  </gml:featureMembers>
                </wfs:FeatureCollection>
                """;
        InputStream targetStream2 = new ByteArrayInputStream(xml6.getBytes());
        FeatureCollectionType obj=(FeatureCollectionType) parser.parse(targetStream2);
        System.out.println(obj);
        Encoder encoder=new Encoder(configuration1);
        encoder.encodeAsString(obj,new QName("http://www.opengis.net/wfs", "FeatureCollectionType"));
    }
    void parse3() throws IOException, ParserConfigurationException, SAXException {
        Configuration configuration1 = new WFSConfiguration();
        Parser parser = new Parser(configuration1);
        String xml6= """
                <DescribeFeatureType
                  version="1.1.0"
                  service="WFS"
                  xmlns="http://www.opengis.net/wfs"
                  xmlns:topp="http://www.openplans.org/topp"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">
                 \s
                    <TypeName>topp:states</TypeName>
                   \s
                </DescribeFeatureType>
                """;
        InputStream targetStream2 = new ByteArrayInputStream(xml6.getBytes());
        DescribeFeatureTypeType obj=(DescribeFeatureTypeType) parser.parse(targetStream2);
        QName qName= (QName) obj.getTypeName().get(0);
        System.out.println(obj);
      }
      void parse4() throws IOException, ParserConfigurationException, SAXException {
          Configuration configuration1 = new WFSConfiguration();
          Configuration configuration=new CustomConfig();
          String xml6= """
                <Node xmlns="file://www.ope/ogc">
                    <name>
                    123
                    </name>
                </Node>
                """;
          InputStream targetStream2 = new ByteArrayInputStream(xml6.getBytes());
          Parser parser = new Parser(configuration);
           Object obj= parser.parse(targetStream2);
          System.out.println(obj);
      }
}
